package com.moonlitdoor.amessage.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation
import com.moonlitdoor.amessage.routes.Routes

@Composable
fun Settings(navHostController: NavHostController, viewModel: SettingsViewModel, setAppChrome: (appChrome: AppChrome) -> Unit) {

  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.settings_title),
      showBottomBar = false,
      navigation = Navigation { navHostController.popBackStack() }
    )
  )
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
  ) {
    val showEmployee by viewModel.isEmployeeSettingsUIEnabled().collectAsState(initial = false)
    val showExperiments by viewModel.isExperimentsUIEnabled().collectAsState(initial = false)
    val showDeveloperSettings by viewModel.isDeveloperSettingsUIEnabled().collectAsState(initial = false)
    if (showEmployee) {
      SettingItem(title = R.string.connect_employee_settings) { navHostController.navigate(Routes.EmployeeSettings.route) }
      SettingItem(title = R.string.settings_employee_settings_disable) { viewModel.disableEmployeeSettingsUI() }
    }
    if (showExperiments) {
      SettingItem(title = R.string.experiments_title) { navHostController.navigate(Routes.Experiments.route) }
      SettingItem(title = R.string.settings_experiments_disable) { viewModel.disableExperimentsUI() }
    }
    if (showDeveloperSettings) {
      SettingItem(title = R.string.connect_developer_settings) { navHostController.navigate(Routes.DeveloperSettings.route) }
      SettingItem(title = R.string.settings_developer_settings_disable) { viewModel.disableDeveloperSettingsUI() }
    }

  }

}

@Preview
@Composable
fun SettingsPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: SettingsViewModel = viewModel()
    Settings(navHostController, viewModel = viewModel) { }
  }
}