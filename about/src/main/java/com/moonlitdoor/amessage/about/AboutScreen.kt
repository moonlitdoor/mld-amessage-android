package com.moonlitdoor.amessage.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun AboutScreen(navHostController: NavHostController, viewModel: AboutViewModel, showBottomBar: (Boolean) -> Unit) {
  Timber.d("About Composable")
  showBottomBar(false)
  val screenState by viewModel.screenState.collectAsState(initial = AboutScreenState.Loading)
  screenState.let { state ->
    Ensure exhaustive when (state) {
      is AboutScreenState.Loading -> Loading(title = R.string.about_title) { navHostController.popBackStack() }
      is AboutScreenState.Loaded -> AboutLoaded(state) { navHostController.popBackStack() }
    }
  }
}
