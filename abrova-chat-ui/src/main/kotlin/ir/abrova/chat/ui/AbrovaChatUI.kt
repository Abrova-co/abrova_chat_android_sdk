/**
 * AbrovaChat UI SDK for Android
 *
 * A comprehensive UI toolkit for building chat interfaces with Jetpack Compose.
 * This library provides ready-to-use components that work seamlessly with
 * the AbrovaChat Core SDK.
 *
 * ## Quick Start
 *
 * ```kotlin
 * // 1. Wrap your app with AbrovaChatScope
 * AbrovaChatScope(
 *     config = AbrovaChatConfig(
 *         apiKey = "your-api-key",
 *         userId = "user-123"
 *     )
 * ) {
 *     // 2. Use ChatScreen for a complete chat experience
 *     ChatScreen(
 *         roomId = "room-456",
 *         currentUserId = "user-123"
 *     )
 * }
 * ```
 *
 * ## Available Components
 *
 * ### State Management
 * - [AbrovaChatScope] - Provides AbrovaChatClient to the composition tree
 * - [AbrovaChatProvider] - Alternative for external client management
 * - [ChatChannelState] - Manages state for a single chat room
 *
 * ### Core Chat Components
 * - [ChatScreen] - Complete chat UI with messages, input, and typing indicator
 * - [ChatMessageBubble] - Individual message display with attachments and reactions
 * - [ChatInputField] - Message input with emoji, attachments, and voice support
 *
 * ### Status Indicators
 * - [TypingIndicator] - Shows who is typing
 * - [PresenceIndicator] - Online/offline status dot
 * - [UnreadBadge] - Unread message count badge
 *
 * ### Message Interactions
 * - [MessageContextMenu] - Long-press menu with actions
 * - [MessageReactionPicker] - Quick emoji reaction picker
 *
 * ### List Components
 * - [ChatRoomListTile] - Room list item with customization
 * - [ChatRoomListTileSkeleton] - Loading placeholder
 *
 * ## Theming
 *
 * Wrap your content with [AbrovaChatTheme] for consistent styling:
 *
 * ```kotlin
 * AbrovaChatTheme(
 *     colors = AbrovaChatColors(...),  // Optional custom colors
 *     dimensions = AbrovaChatDimensions(...)  // Optional custom dimensions
 * ) {
 *     ChatScreen(...)
 * }
 * ```
 *
 * @see ir.abrova.chat.AbrovaChatClient
 * @see ir.abrova.chat.AbrovaChatConfig
 */
package ir.abrova.chat.ui

// Re-export state management
import ir.abrova.chat.ui.state.ChatChannelState
import ir.abrova.chat.ui.state.AbrovaChatProvider
import ir.abrova.chat.ui.state.AbrovaChatScope
import ir.abrova.chat.ui.state.LocalAbrovaChatClient
import ir.abrova.chat.ui.state.rememberChatChannelState

// Re-export theme
import ir.abrova.chat.ui.theme.AbrovaChatColors
import ir.abrova.chat.ui.theme.AbrovaChatDimensions
import ir.abrova.chat.ui.theme.AbrovaChatTheme
import ir.abrova.chat.ui.theme.LocalAbrovaChatColors
import ir.abrova.chat.ui.theme.LocalAbrovaChatDimensions

// Re-export components
import ir.abrova.chat.ui.components.AttachmentPickerContent
import ir.abrova.chat.ui.components.ChatInputField
import ir.abrova.chat.ui.components.ChatMessageBubble
import ir.abrova.chat.ui.components.ChatRoomListTile
import ir.abrova.chat.ui.components.ChatRoomListTileSkeleton
import ir.abrova.chat.ui.components.ChatScreen
import ir.abrova.chat.ui.components.CompactTypingIndicator
import ir.abrova.chat.ui.components.FileUploader
import ir.abrova.chat.ui.components.MessageAction
import ir.abrova.chat.ui.components.MessageContextMenu
import ir.abrova.chat.ui.components.MessageReactionPicker
import ir.abrova.chat.ui.components.MessageReactions
import ir.abrova.chat.ui.components.PresenceIndicator
import ir.abrova.chat.ui.components.PresenceStatus
import ir.abrova.chat.ui.components.TypingDots
import ir.abrova.chat.ui.components.TypingIndicator
import ir.abrova.chat.ui.components.UnreadBadge
import ir.abrova.chat.ui.components.UnreadDot
import ir.abrova.chat.ui.components.defaultReactions

// Re-export new components (matching Flutter SDK)
import ir.abrova.chat.ui.components.SwipeableMessage
import ir.abrova.chat.ui.components.ReplyPreview
import ir.abrova.chat.ui.components.ReplyInputPreview
import ir.abrova.chat.ui.components.ReadReceipts
import ir.abrova.chat.ui.components.ReadReceiptUser
import ir.abrova.chat.ui.components.ReadReceiptDetails
import ir.abrova.chat.ui.components.MessageStatus
import ir.abrova.chat.ui.components.MentionsList
import ir.abrova.chat.ui.components.MentionUser
import ir.abrova.chat.ui.components.MentionsController
import ir.abrova.chat.ui.components.rememberMentionsController
import ir.abrova.chat.ui.components.VoiceMessageRecorder
import ir.abrova.chat.ui.components.VoiceMessagePlayer
import ir.abrova.chat.ui.components.VoiceRecorderState
import ir.abrova.chat.ui.components.VoiceRecordingResult
import ir.abrova.chat.ui.components.MessageSearchBar
import ir.abrova.chat.ui.components.MessageSearchOverlay
import ir.abrova.chat.ui.components.HighlightedSearchText
import ir.abrova.chat.ui.components.LinkPreview
import ir.abrova.chat.ui.components.LinkPreviewData
import ir.abrova.chat.ui.components.LinkPreviewComposer
import ir.abrova.chat.ui.components.LinkExtractor
import ir.abrova.chat.ui.components.ChatAttachmentPicker
import ir.abrova.chat.ui.components.ChatAttachmentPickerContent
import ir.abrova.chat.ui.components.AttachmentType
import ir.abrova.chat.ui.components.AttachmentResult
