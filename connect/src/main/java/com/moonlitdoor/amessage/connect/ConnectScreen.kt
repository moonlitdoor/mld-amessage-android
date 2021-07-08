package com.moonlitdoor.amessage.connect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun ConnectScreen(viewModel: ConnectViewModel) {
  Timber.d("ConnectScreen")

  val viewState by viewModel.connectViewState.collectAsState(initial = ConnectViewState.Loading)
  viewState.let { state ->
    Ensure exhaustive when (state) {
      is ConnectViewState.Loading -> Loading(title = R.string.connect_title)
      is ConnectViewState.Loaded -> ConnectLoaded(
        state = state,
        executor = viewModel.executor,
        pendingConfirm = { viewModel.confirm(it) },
        pendingReject = { viewModel.reject(it) },
        detect = { viewModel.detector.detectInImage(it) },
        connectionFound = { connection, imageProxy -> viewModel.connectionFound(connection, imageProxy) },
        experimentsCodeFound = { viewModel.experimentsCodeFound(it) },
        developerSettingsCodeFound = { viewModel.developerSettingsCodeFound(it) },
        employeeSettingsCodeFound = { viewModel.employeeSettingsCodeFound(it) },
        createConnection = { viewModel.create(it) },
        enableExperiments = { viewModel.enableExperiments() },
        enableDeveloperSettings = { viewModel.enableDeveloperSettings() },
        enableEmployeeSettings = { viewModel.enableEmployeeSettings() },
        cancelCurrentScan = { viewModel.cancelCurrentScan() }
      )
    }
  }
}
