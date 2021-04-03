package com.moonlitdoor.amessage.connections

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.extensions.Ensure

@Composable
fun Connections(navHostController: NavHostController, viewModel: ConnectionsViewModel) {
  val viewState by viewModel.viewState.collectAsState(initial = ConnectionsViewState.Loading)
  Ensure exhaustive when (viewState) {
    is ConnectionsViewState.Loading -> ConnectionsLoading()
    is ConnectionsViewState.Empty -> ConnectionsEmpty(navHostController = navHostController)
    is ConnectionsViewState.Result -> ConnectionsResult(navHostController = navHostController, viewState = viewState as ConnectionsViewState.Result)
  }

}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: ConnectionsViewModel = viewModel()
    Connections(navHostController, viewModel)
  }
}
