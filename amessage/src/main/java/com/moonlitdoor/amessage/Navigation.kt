package com.moonlitdoor.amessage

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moonlitdoor.amessage.connect.Connect
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connections.Connections
import com.moonlitdoor.amessage.connections.ConnectionsViewModel
import com.moonlitdoor.amessage.conversations.Conversations
import com.moonlitdoor.amessage.conversations.ConversationsViewModel
import com.moonlitdoor.amessage.handle.Handle
import com.moonlitdoor.amessage.handle.HandleViewModel

@Composable
fun Navigation(navController: NavHostController, showBottomBar: (Boolean) -> Unit) {
  NavHost(navController, startDestination = Screen.Conversations.route) {
    composable(Screen.Conversations.route) {
      showBottomBar(Screen.Conversations.showBottomBar)
      val viewModel: ConversationsViewModel = hiltNavGraphViewModel()
      Conversations(navHostController = navController, viewModel = viewModel)
    }
    composable(Screen.Connections.route) {
      showBottomBar(Screen.Connections.showBottomBar)
      val viewModel: ConnectionsViewModel = hiltNavGraphViewModel()
      Connections(navHostController = navController, viewModel = viewModel)
    }
    composable(Screen.Connect.route) {
      showBottomBar(Screen.Connect.showBottomBar)
      val viewModel: ConnectViewModel = hiltNavGraphViewModel()
      Connect(navHostController = navController, viewModel = viewModel)
    }
    composable(Screen.Handle.route) {
      showBottomBar(Screen.Handle.showBottomBar)
      val viewModel: HandleViewModel = hiltNavGraphViewModel()
      Handle(navHostController = navController, viewModel = viewModel)
    }
  }
}