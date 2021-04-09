package com.moonlitdoor.amessage

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.connect.Connect
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connection.Connection
import com.moonlitdoor.amessage.connection.ConnectionViewModel
import com.moonlitdoor.amessage.connections.Connections
import com.moonlitdoor.amessage.connections.ConnectionsViewModel
import com.moonlitdoor.amessage.conversations.Conversations
import com.moonlitdoor.amessage.conversations.ConversationsViewModel
import com.moonlitdoor.amessage.handle.Handle
import com.moonlitdoor.amessage.handle.HandleViewModel
import com.moonlitdoor.amessage.routes.Routes

@Composable
fun Navigation(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {
  NavHost(navHostController, startDestination = Routes.Conversations.route) {
    composable(route = Routes.Conversations.route) {
      val viewModel: ConversationsViewModel = hiltNavGraphViewModel()
      Conversations(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Connections.route) {
      val viewModel: ConnectionsViewModel = hiltNavGraphViewModel()
      Connections(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Connection.route, arguments = Routes.Connection.arguments) { backStackEntry ->
      val connectionId = backStackEntry.arguments?.getLong(Routes.Connection.Arguments.CONNECTION_ID)
      connectionId ?: throw IllegalArgumentException("ConnectionId can not be null")
      val viewModel: ConnectionViewModel = hiltNavGraphViewModel()
      Connection(navHostController = navHostController, viewModel = viewModel, connectionId = connectionId, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Connect.route) {
      val viewModel: ConnectViewModel = hiltNavGraphViewModel()
      Connect(viewModel = viewModel, setAppChrome = setAppChrome)
    }
    composable(route = Routes.Handle.route) {
      val viewModel: HandleViewModel = hiltNavGraphViewModel()
      Handle(navHostController = navHostController, viewModel = viewModel, setAppChrome = setAppChrome)
    }
  }
}