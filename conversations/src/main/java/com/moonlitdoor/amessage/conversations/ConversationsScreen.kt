package com.moonlitdoor.amessage.conversations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import com.moonlitdoor.amessage.routes.Routes
import com.moonlitdoor.amessage.routes.navigate
import timber.log.Timber

@Composable
fun ConversationsScreen(navHostController: NavHostController, viewModel: ConversationsViewModel, showBottomBar: (Boolean) -> Unit) {
  Timber.d("ConversationsScreen")
  showBottomBar(true)
  val screenState by viewModel.screenState.collectAsState(initial = ConversationsScreenState.Loading)
  screenState.let { state ->
    Ensure exhaustive when (state) {
      is ConversationsScreenState.HandleNotSet -> navHostController.navigate(Routes.Handle) {
        launchSingleTop = true
      }
      is ConversationsScreenState.Loading -> Loading(R.string.conversations_title) { navHostController.popBackStack() }
      is ConversationsScreenState.Empty -> ConversationsEmpty { navHostController.navigate(it) }
      is ConversationsScreenState.Result -> ConversationsResult(screenState = state) { navHostController.navigate(it) }
    }
  }
}
