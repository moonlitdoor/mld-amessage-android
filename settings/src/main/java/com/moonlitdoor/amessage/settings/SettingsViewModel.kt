package com.moonlitdoor.amessage.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: SettingsRepository) : ViewModel() {

  val screenState: Flow<SettingsScreenState> = combine(repository.experimentsUIEnabled(), repository.developerSettingsUIEnabled(), repository.employeeSettingsUIEnabled()) { experiments, developer, employee ->
    SettingsScreenState.Data(experiments, developer, employee)
  }

  fun enableExperimentsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setExperimentsUiEnabled()
  }

  fun disableExperimentsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setExperimentsUiDisabled()
  }

  fun enableDeveloperSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setDeveloperSettingsEnabled()
  }

  fun disableDeveloperSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setDeveloperSettingsDisabled()
  }

  fun enableEmployeeSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setEmployeeSettingsEnabled()
  }

  fun disableEmployeeSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setEmployeeSettingsDisabled()
  }

  fun clearDatabase() = viewModelScope.launch(Dispatchers.IO) {
    repository.clearDatabase()
  }
}
