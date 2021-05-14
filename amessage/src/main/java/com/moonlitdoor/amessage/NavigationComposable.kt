package com.moonlitdoor.amessage

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moonlitdoor.amessage.about.About
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.connect.Connect
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connection.Connection
import com.moonlitdoor.amessage.connection.ConnectionViewModel
import com.moonlitdoor.amessage.connections.Connections
import com.moonlitdoor.amessage.connections.ConnectionsViewModel
import com.moonlitdoor.amessage.conversation.Conversation
import com.moonlitdoor.amessage.conversation.ConversationViewModel
import com.moonlitdoor.amessage.conversations.Conversations
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
import com.moonlitdoor.amessage.settings.DeveloperSettings
import com.moonlitdoor.amessage.settings.EmployeeSettings
import com.moonlitdoor.amessage.settings.Settings
import com.moonlitdoor.amessage.settings.SettingsViewModel
import com.moonlitdoor.amessage.windows.Windows
import timber.log.Timber
import java.util.UUID

@Composable
fun Navigation(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {
  Timber.d("Navigation Composable")
  NavHost(navHostController, startDestination = Routes.Conversations.route) {
    composable(route = Routes.Handle.route) {
      val viewModel: HandleViewModel = hiltNavGraphViewModel(it)
      Handle(
        navHostController = navHostController,
        viewModel = viewModel,
        setAppChrome = setAppChrome
      )
    }
    composable(route = Routes.Conversations.route) {
      val viewModel: ConversationsViewModel = hiltNavGraphViewModel(it)
      Conversations(
        navHostController = navHostController,
        viewModel = viewModel,
        setAppChrome = setAppChrome
      )
    }
    composable(route = Routes.Conversation.route, arguments = Routes.Conversation.arguments) { backStackEntry ->
      val conversationId = backStackEntry.arguments?.getString(Routes.Conversation.Arguments.CONVERSATION_ID)
      conversationId ?: throw IllegalArgumentException("conversationId can not be null")
      val viewModel: ConversationViewModel = hiltNavGraphViewModel(backStackEntry)
      Conversation(
        navHostController = navHostController,
        viewModel = viewModel,
        conversationId = UUID.fromString(conversationId),
        setAppChrome = setAppChrome
      )
    }
    composable(route = Routes.Connections.route) {
      val viewModel: ConnectionsViewModel = hiltNavGraphViewModel(it)
      Connections(
        navHostController = navHostController,
        viewModel = viewModel,
        setAppChrome = setAppChrome
      )
    }
    composable(route = Routes.Connection.route, arguments = Routes.Connection.arguments) { backStackEntry ->
      val connectionId = backStackEntry.arguments?.getString(Routes.Connection.Arguments.CONNECTION_ID)
      connectionId ?: throw IllegalArgumentException("connectionId can not be null")
      val viewModel: ConnectionViewModel = hiltNavGraphViewModel(backStackEntry)
      viewModel.setConnectionId(UUID.fromString(connectionId))
      Connection(
        navHostController = navHostController,
        viewModel = viewModel,
        connectionId = UUID.fromString(connectionId),
        setAppChrome = setAppChrome
      )
    }
    composable(route = Routes.Connect.route) {
      val viewModel: ConnectViewModel = hiltNavGraphViewModel(it)
      Connect(
        viewModel = viewModel,
        setAppChrome = setAppChrome
      )
    }
    composable(route = Routes.More.route) {
      val viewModel: MoreViewModel = hiltNavGraphViewModel(it)
      More(
        navHostController = navHostController,
        viewModel = viewModel,
        setAppChrome = setAppChrome
      )
    }
    composable(route = Routes.About.route) {
      About(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.FAQ.route) {
      val viewModel: FaqViewModel = hiltNavGraphViewModel(it)
      Faq(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.WhatsNew.route) {
      WhatsNew(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Feedback.route) {
      Feedback(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Help.route) {
      Help(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Windows.route) {
      Windows(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Settings.route) {
      val viewModel: SettingsViewModel = hiltNavGraphViewModel(it)
      Settings(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.EmployeeSettings.route) {
      val viewModel: SettingsViewModel = hiltNavGraphViewModel(it)
      EmployeeSettings(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.DeveloperSettings.route) {
      val viewModel: SettingsViewModel = hiltNavGraphViewModel(it)
      DeveloperSettings(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Experiments.route) {
      ExperimentsUi(navHostController = navHostController, setAppChrome = setAppChrome)
    }
  }
}
