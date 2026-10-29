package ir.abrova.chat.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import ir.abrova.chat.AbrovaChatClient
import ir.abrova.chat.AbrovaChatConfig

/**
 * CompositionLocal for accessing AbrovaChatClient throughout the compose tree.
 */
val LocalAbrovaChatClient = staticCompositionLocalOf<AbrovaChatClient> {
    error("No AbrovaChatClient provided. Wrap your composable with AbrovaChatScope or AbrovaChatProvider.")
}

/**
 * Provides AbrovaChatClient to the composition tree.
 * This variant creates and manages the client lifecycle automatically.
 *
 * @param config The AbrovaChat configuration.
 * @param autoConnect Whether to automatically connect on composition.
 * @param onConnected Callback when connection is established.
 * @param onConnectionError Callback when connection fails.
 * @param content The composable content.
 */
@Composable
fun AbrovaChatScope(
    config: AbrovaChatConfig,
    autoConnect: Boolean = true,
    onConnected: (() -> Unit)? = null,
    onConnectionError: ((Throwable) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val client = rememberAbrovaChatClient(config)

    LaunchedEffect(client, autoConnect) {
        if (autoConnect) {
            try {
                client.connect()
                onConnected?.invoke()
            } catch (e: Exception) {
                onConnectionError?.invoke(e)
            }
        }
    }

    DisposableEffect(client) {
        onDispose {
            client.disconnect()
        }
    }

    CompositionLocalProvider(LocalAbrovaChatClient provides client) {
        content()
    }
}

/**
 * Provides an existing AbrovaChatClient to the composition tree.
 * Use this when you manage the client lifecycle externally.
 *
 * @param client The pre-configured AbrovaChatClient instance.
 * @param content The composable content.
 */
@Composable
fun AbrovaChatProvider(
    client: AbrovaChatClient,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalAbrovaChatClient provides client) {
        content()
    }
}

/**
 * Remembers a AbrovaChatClient instance that survives recomposition.
 * Creates a new client if the config changes.
 */
@Composable
private fun rememberAbrovaChatClient(config: AbrovaChatConfig): AbrovaChatClient {
    return androidx.compose.runtime.remember(config) {
        AbrovaChatClient(config)
    }
}
