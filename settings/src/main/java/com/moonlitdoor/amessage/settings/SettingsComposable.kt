package com.moonlitdoor.amessage.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber

@Composable
fun Settings(navHostController: NavHostController, viewModel: SettingsViewModel, showBottomBar: (Boolean) -> Unit) {
  Timber.d("Settings Composable")
  var clicks: Int by remember { mutableStateOf(value = 1) }
  showBottomBar(false)
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.settings_title),
            modifier = Modifier.clickable(
              interactionSource = remember { MutableInteractionSource() },
              indication = null,
            ) {
              when (clicks) {
                in 1..5 -> clicks++
                6 -> navHostController.navigate(Routes.DeveloperSettings.route)
              }
            })
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
      }
      if (showExperiments) {
        SettingItem(title = R.string.experiments_title) { navHostController.navigate(Routes.Experiments.route) }
      }
      if (showDeveloperSettings) {
        SettingItem(title = R.string.connect_developer_settings) { navHostController.navigate(Routes.DeveloperSettings.route) }
      }
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
