package com.moonlitdoor.amessage.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

  val showEmployee by viewModel.isEmployeeSettingsUIEnabled().collectAsState(initial = false)
  val showDeveloperSettings by viewModel.isDeveloperSettingsUIEnabled().collectAsState(initial = false)
  val showExperiments by viewModel.isExperimentsUIEnabled().collectAsState(initial = false)

  Column {
    SettingSwitchItem(title = R.string.connect_employee_settings, checked = showEmployee) {
      if (it) viewModel.enableEmployeeSettingsUI() else viewModel.disableEmployeeSettingsUI()
    }

    SettingSwitchItem(title = R.string.connect_experiments, checked = showExperiments) {
      if (it) viewModel.enableExperimentsUI() else viewModel.disableExperimentsUI()
    }
    SettingSwitchItem(title = R.string.connect_developer_settings, checked = showDeveloperSettings) {
      if (it) viewModel.enableDeveloperSettingsUI() else viewModel.disableDeveloperSettingsUI()
    }
    SettingItem(title = "Clear Database", "Clear all 'connections' and 'conversations'.") {
      viewModel.clearDatabase()
    }
  }
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
