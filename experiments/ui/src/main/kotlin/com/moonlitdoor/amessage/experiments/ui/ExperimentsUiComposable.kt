package com.moonlitdoor.amessage.experiments.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation
import com.moonlitdoor.amessage.experiments.Experiments
import timber.log.Timber

@Composable
fun ExperimentsUi(navHostController: NavHostController, setAppChrome: (AppChrome) -> Unit) {
  setAppChrome(AppChrome(
    title = stringResource(id = R.string.experiments_title),
    navigation = Navigation { navHostController.popBackStack() }
  ))

  LazyColumn {
    items(Experiments.experiments) {
      Timber.d("$it")
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
      ) {
        ExperimentsItem(item = it)
      }
    }
  }
}

@Preview
@Composable
fun ExperimentsUiPreview() {
  val navHostController = rememberNavController()
  ExperimentsUi(navHostController) {}
}