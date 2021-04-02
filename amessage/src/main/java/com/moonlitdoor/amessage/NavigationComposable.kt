package com.moonlitdoor.amessage

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moonlitdoor.amessage.components.ActionItem
import com.moonlitdoor.amessage.connect.Connect
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connections.Connections
import com.moonlitdoor.amessage.connections.ConnectionsViewModel
import com.moonlitdoor.amessage.conversations.Conversations
import com.moonlitdoor.amessage.conversations.ConversationsViewModel
import com.moonlitdoor.amessage.handle.Handle
import com.moonlitdoor.amessage.handle.HandleViewModel

@Composable
fun Navigation(navHostController: NavHostController, setCurrentActions: (actionItems: List<ActionItem>) -> Unit, currentScreen: (Screen) -> Unit) {
  NavHost(navHostController, startDestination = Screen.Conversations.route) {
    composable(Screen.Conversations.route) {
      currentScreen(Screen.Conversations)
      val viewModel: ConversationsViewModel = hiltNavGraphViewModel()
      Conversations(navHostController = navHostController, viewModel = viewModel, setCurrentActions = setCurrentActions)
    }
    composable(Screen.Connections.route) {
      currentScreen(Screen.Connections)
      val viewModel: ConnectionsViewModel = hiltNavGraphViewModel()
      Connections(navHostController = navHostController, viewModel = viewModel)
    }
    composable(Screen.Connect.route) {
      currentScreen(Screen.Connect)
      val viewModel: ConnectViewModel = hiltNavGraphViewModel()
      Connect(navHostController = navHostController, viewModel = viewModel)
    }
    composable(Screen.Handle.route) {
      currentScreen(Screen.Handle)
      val viewModel: HandleViewModel = hiltNavGraphViewModel()
      Handle(navHostController = navHostController, viewModel = viewModel, setCurrentActions = setCurrentActions)
    }
  }
}