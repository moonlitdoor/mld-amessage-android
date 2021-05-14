package com.moonlitdoor.amessage.connection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber
import java.util.UUID

@Composable
fun Connection(navHostController: NavHostController, viewModel: ConnectionViewModel, connectionId: UUID, setAppChrome: (appChrome: AppChrome) -> Unit) {
  Timber.d("Connection Composable")
//  setAppChrome(
//    AppChrome(
//      title = stringResource(id = R.string.connection_title),
//      navigation = Navigation { navHostController.popBackStack() },
//      fab = {
//        FloatingActionButton(onClick = { viewModel.createConversation(title = "title", topic = null) }) {
//          Icon(painterResource(id = R.drawable.ic_baseline_chat_24), null)
//        }
//      },
//    )
//  )

  val viewState by viewModel.viewState.collectAsState(initial = ConnectionViewState.Loading)
  viewState.let { state ->
    Ensure exhaustive when (state) {
      is ConnectionViewState.Loading -> Loading()
      is ConnectionViewState.Empty -> ConnectionEmpty()
      is ConnectionViewState.Result -> ConnectionResult(navHostController = navHostController, viewModel = viewModel, state = state, setAppChrome)
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun ConnectionPreview() {
  val navHostController = rememberNavController()
  val viewModel: ConnectionViewModel = viewModel()
  Connection(navHostController = navHostController, viewModel = viewModel, connectionId = UUID.randomUUID()) {}
}
