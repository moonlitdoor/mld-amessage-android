package com.moonlitdoor.amessage.connections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import com.moonlitdoor.amessage.routes.navigate
import timber.log.Timber

@Composable
fun ConnectionsScreen(navHostController: NavHostController, viewModel: ConnectionsViewModel, showBottomBar: (Boolean) -> Unit) {
  Timber.d("ConnectionsScreen")
  showBottomBar(true)
  val screenState by viewModel.screenState.collectAsState(initial = ConnectionsScreenState.Loading)
  screenState.let { state ->
    Ensure exhaustive when (state) {
      is ConnectionsScreenState.Loading -> Loading(R.string.connections_title) { navHostController.popBackStack() }
      is ConnectionsScreenState.Empty -> ConnectionsEmpty { navHostController.navigate(it) }
      is ConnectionsScreenState.Result -> ConnectionsResult(screenState = state) { navHostController.navigate(it) }
    }
  }
}
