package ir.abrova.chat.example.ecommerce

import android.app.Activity
import android.os.Bundle
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ir.abrova.chat.example.ecommerce.models.DemoUser
import ir.abrova.chat.example.ecommerce.screens.LoginScreen
import ir.abrova.chat.example.ecommerce.screens.OrderChatScreen
import ir.abrova.chat.example.ecommerce.screens.OrdersScreen
import ir.abrova.chat.example.ecommerce.ui.theme.AbrovaChatEcommerceTheme
import ir.abrova.chat.AbrovaChatClient
import ir.abrova.chat.AbrovaChatConfig
import ir.abrova.chat.ui.state.AbrovaChatProvider
import ir.abrova.push.sdk.AbrovaPush

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbrovaChatEcommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EcommerceApp()
                }
            }
        }
    }
}

@Composable
fun EcommerceApp() {
    var currentUser by remember { mutableStateOf<DemoUser?>(null) }
    var abrovaChatClient by remember { mutableStateOf<AbrovaChatClient?>(null) }
    val activity = LocalContext.current as? Activity

    val navController = rememberNavController()

    // Handle login
    val onLogin: (DemoUser) -> Unit = { user ->
        currentUser = user
        val client = AbrovaChatClient(
            config = AbrovaChatConfig(
                apiKey = BuildConfig.ABROVA_API_KEY,
                userId = user.id
            )
        )
        client.connect()
        abrovaChatClient = client

        // Request notification permission and register for push
        activity?.let { AbrovaPush.requestNotificationPermission(it) }
        AbrovaPush.register(user.id)

        navController.navigate("orders") {
            popUpTo("login") { inclusive = true }
        }
    }

    // Handle logout
    val onLogout: () -> Unit = {
        abrovaChatClient?.disconnect()
        abrovaChatClient = null
        currentUser = null
        AbrovaPush.unregister()
        navController.navigate("login") {
            popUpTo(0) { inclusive = true }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) "orders" else "login"
    ) {
        composable("login") {
            LoginScreen(onLogin = onLogin)
        }

        composable("orders") {
            val client = abrovaChatClient
            val user = currentUser
            if (client != null && user != null) {
                AbrovaChatProvider(client = client) {
                    OrdersScreen(
                        currentUser = user,
                        onOrderClick = { orderId ->
                            navController.navigate("chat/$orderId")
                        },
                        onLogout = onLogout
                    )
                }
            }
        }

        composable(
            route = "chat/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: return@composable
            val client = abrovaChatClient
            val user = currentUser
            if (client != null && user != null) {
                AbrovaChatProvider(client = client) {
                    OrderChatScreen(
                        orderId = orderId,
                        currentUser = user,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
