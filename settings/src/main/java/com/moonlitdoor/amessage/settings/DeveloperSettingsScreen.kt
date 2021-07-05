package com.moonlitdoor.amessage.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.extensions.Ensure
import com.moonlitdoor.amessage.settings.items.SettingItem
import com.moonlitdoor.amessage.settings.items.SettingSwitchItem
import timber.log.Timber

@Composable
fun DeveloperSettingsScreen(navHostController: NavHostController, viewModel: SettingsViewModel, showBottomBar: (Boolean) -> Unit) {
  Timber.d("DeveloperSettings Composable")
  showBottomBar(false)
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.connect_developer_settings))
        },
        elevation = 12.dp,
        navigationIcon = {
          IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = stringResource(R.string.connect_ok)
            )
          }
        },
      )
    },
  ) {
    val screenState by viewModel.screenState.collectAsState(initial = SettingsScreenState.Data(experiments = false, developer = false, employee = false))
    screenState.let { state ->
      Ensure exhaustive when (state) {
        is SettingsScreenState.Loading -> {
        }
        is SettingsScreenState.Data -> Column {
          SettingSwitchItem(title = R.string.connect_employee_settings, checked = state.employee) {
            if (it) viewModel.enableEmployeeSettingsUI() else viewModel.disableEmployeeSettingsUI()
          }

          SettingSwitchItem(title = R.string.connect_experiments, checked = state.experiments) {
            if (it) viewModel.enableExperimentsUI() else viewModel.disableExperimentsUI()
          }
          SettingSwitchItem(title = R.string.connect_developer_settings, checked = state.developer) {
            if (it) viewModel.enableDeveloperSettingsUI() else viewModel.disableDeveloperSettingsUI()
          }
          SettingItem(title = "Clear Database", description = "Clear all 'connections' and 'conversations'.") {
            viewModel.clearDatabase()
          }
        }
      }
    }
  }
}

@Preview
@Composable
fun DeveloperSettingsPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: SettingsViewModel = viewModel()
    DeveloperSettingsScreen(navHostController, viewModel = viewModel) { }
  }
}
