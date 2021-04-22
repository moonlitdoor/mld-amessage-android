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
import com.moonlitdoor.amessage.conversations.Conversations
import com.moonlitdoor.amessage.conversations.ConversationsViewModel
import com.moonlitdoor.amessage.experiments.ui.ExperimentsUi
import com.moonlitdoor.amessage.faq.Faq
import com.moonlitdoor.amessage.feedback.Feedback
import com.moonlitdoor.amessage.handle.Handle
import com.moonlitdoor.amessage.handle.HandleViewModel
import com.moonlitdoor.amessage.help.Help
import com.moonlitdoor.amessage.more.More
import com.moonlitdoor.amessage.news.WhatsNew
import com.moonlitdoor.amessage.routes.Routes
import com.moonlitdoor.amessage.settings.Settings
import com.moonlitdoor.amessage.windows.Windows
import java.util.*

@Composable
fun Navigation(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {
  NavHost(navHostController, startDestination = Routes.Conversations.route) {
    composable(route = Routes.Handle.route) {
      val viewModel: HandleViewModel = hiltNavGraphViewModel()
      Handle(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Conversations.route) {
      val viewModel: ConversationsViewModel = hiltNavGraphViewModel()
      Conversations(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Connections.route) {
      val viewModel: ConnectionsViewModel = hiltNavGraphViewModel()
      Connections(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Connection.route, arguments = Routes.Connection.arguments) { backStackEntry ->
      val connectionId = backStackEntry.arguments?.getString(Routes.Connection.Arguments.CONNECTION_ID)
      connectionId ?: throw IllegalArgumentException("ConnectionId can not be null")
      val viewModel: ConnectionViewModel = hiltNavGraphViewModel()
      Connection(navHostController = navHostController, viewModel = viewModel, connectionId = UUID.fromString(connectionId), setAppChrome = setAppChrome)
    }
    composable(route = Routes.Connect.route) {
      val viewModel: ConnectViewModel = hiltNavGraphViewModel()
      Connect(viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.More.route) {
      More(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.About.route) {
      About(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.FAQ.route) {
      Faq(navHostController = navHostController, setAppChrome = setAppChrome)
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
      Settings(navHostController = navHostController, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Experiments.route) {
      ExperimentsUi(navHostController = navHostController, setAppChrome = setAppChrome)
    }
  }
}