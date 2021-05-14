package com.moonlitdoor.amessage.conversations

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber

@Composable
fun Conversations(navHostController: NavHostController, viewModel: ConversationsViewModel, setAppChrome: (appChrome: AppChrome) -> Unit) {
  Timber.d("Conversations Composable")
  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.conversations_title),
      showBottomBar = true
    )
  )

  val viewState by viewModel.viewState.collectAsState(initial = ConversationsViewState.Loading)
  viewState.let { state ->
    Ensure exhaustive when (state) {
      is ConversationsViewState.HandleNotSet -> navHostController.navigate(Routes.Handle.route) {
        launchSingleTop = true
      }
      is ConversationsViewState.Loading -> Loading()
      is ConversationsViewState.Empty -> ConversationsEmpty(navHostController = navHostController)
      is ConversationsViewState.Result -> ConversationsResult(navHostController = navHostController, viewState = state)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: ConversationsViewModel = viewModel()
    Conversations(navHostController, viewModel) {}
  }
}
