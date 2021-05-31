package com.moonlitdoor.amessage

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moonlitdoor.amessage.about.AboutScreen
import com.moonlitdoor.amessage.about.AboutViewModel
import com.moonlitdoor.amessage.connect.Connect
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connection.Connection
import com.moonlitdoor.amessage.connection.ConnectionViewModel
import com.moonlitdoor.amessage.connections.ConnectionsScreen
import com.moonlitdoor.amessage.connections.ConnectionsViewModel
import com.moonlitdoor.amessage.conversation.Conversation
import com.moonlitdoor.amessage.conversation.ConversationViewModel
import com.moonlitdoor.amessage.conversations.ConversationsScreen
import com.moonlitdoor.amessage.conversations.ConversationsViewModel
import com.moonlitdoor.amessage.experiments.ui.ExperimentsUi
import com.moonlitdoor.amessage.faq.Faq
import com.moonlitdoor.amessage.faq.FaqViewModel
import com.moonlitdoor.amessage.feedback.Feedback
import com.moonlitdoor.amessage.handle.Handle
import com.moonlitdoor.amessage.handle.HandleViewModel
import com.moonlitdoor.amessage.help.Help
import com.moonlitdoor.amessage.more.More
import com.moonlitdoor.amessage.more.MoreViewModel
import com.moonlitdoor.amessage.news.WhatsNew
import com.moonlitdoor.amessage.routes.Routes
import com.moonlitdoor.amessage.settings.DeveloperSettingsScreen
import com.moonlitdoor.amessage.settings.EmployeeSettingsScreen
import com.moonlitdoor.amessage.settings.Settings
import com.moonlitdoor.amessage.settings.SettingsViewModel
import com.moonlitdoor.amessage.windows.Windows
import timber.log.Timber
import java.util.UUID

@Composable
fun Navigation(navHostController: NavHostController, showBottomBar: (Boolean) -> Unit) {
  Timber.d("Navigation Composable")
  NavHost(navHostController, startDestination = Routes.Conversations.route) {
    composable(route = Routes.Handle.route) {
      val viewModel: HandleViewModel = hiltViewModel(it)
      Handle(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar
      )
    }
    composable(route = Routes.Conversations.route) {
      val viewModel: ConversationsViewModel = hiltViewModel(it)
      ConversationsScreen(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Conversation.route, arguments = Routes.Conversation.arguments) { backStackEntry ->
      val conversationId = backStackEntry.arguments?.getString(Routes.Conversation.Arguments.CONVERSATION_ID)
      conversationId ?: throw IllegalArgumentException("conversationId can not be null")
      val viewModel: ConversationViewModel = hiltViewModel(backStackEntry)
      Conversation(
        navHostController = navHostController,
        viewModel = viewModel,
        conversationId = UUID.fromString(conversationId),
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Connections.route) {
      val viewModel: ConnectionsViewModel = hiltViewModel(it)
      ConnectionsScreen(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Connection.route, arguments = Routes.Connection.arguments) { backStackEntry ->
      val connectionId = backStackEntry.arguments?.getString(Routes.Connection.Arguments.CONNECTION_ID)
      connectionId ?: throw IllegalArgumentException("connectionId can not be null")
      val viewModel: ConnectionViewModel = hiltViewModel(backStackEntry)
      viewModel.setConnectionId(UUID.fromString(connectionId))
      Connection(
        navHostController = navHostController,
        viewModel = viewModel,
        connectionId = UUID.fromString(connectionId),
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Connect.route) {
      val viewModel: ConnectViewModel = hiltViewModel(it)
      Connect(
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.More.route) {
      val viewModel: MoreViewModel = hiltViewModel(it)
      More(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.About.route) {
      val viewModel: AboutViewModel = hiltViewModel(it)
      AboutScreen(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.FAQ.route) {
      val viewModel: FaqViewModel = hiltViewModel(it)
      Faq(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.WhatsNew.route) {
      WhatsNew(
        navHostController = navHostController,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Feedback.route) {
      Feedback(
        navHostController = navHostController,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Help.route) {
      Help(
        navHostController = navHostController,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Windows.route) {
      Windows(
        navHostController = navHostController,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Settings.route) {
      val viewModel: SettingsViewModel = hiltViewModel(it)
      Settings(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.EmployeeSettings.route) {
      val viewModel: SettingsViewModel = hiltViewModel(it)
      EmployeeSettingsScreen(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.DeveloperSettings.route) {
      val viewModel: SettingsViewModel = hiltViewModel(it)
      DeveloperSettingsScreen(
        navHostController = navHostController,
        viewModel = viewModel,
        showBottomBar = showBottomBar,
      )
    }
    composable(route = Routes.Experiments.route) {
      ExperimentsUi(
        navHostController = navHostController,
        showBottomBar = showBottomBar,
      )
    }
  }
}
