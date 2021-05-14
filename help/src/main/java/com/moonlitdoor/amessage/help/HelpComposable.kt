package com.moonlitdoor.amessage.help

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation
import timber.log.Timber

@Composable
fun Help(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {
  Timber.d("Help Composable")
  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.help_title),
      showBottomBar = false,
      navigation = Navigation { navHostController.popBackStack() }
    )
  )
}

@Preview
@Composable
fun HelpPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    Help(navHostController) { }
  }
}
