package com.moonlitdoor.amessage.settings

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation

@Composable
fun DeveloperSettings(navHostController: NavHostController, viewModel: SettingsViewModel, setAppChrome: (appChrome: AppChrome) -> Unit) {

  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.connect_developer_settings),
      showBottomBar = false,
      navigation = Navigation { navHostController.popBackStack() }
    )
  )
}

@Preview
@Composable
fun DeveloperSettingsPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: SettingsViewModel = viewModel()
    DeveloperSettings(navHostController, viewModel = viewModel) { }
  }
}