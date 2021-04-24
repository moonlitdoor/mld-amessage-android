package com.moonlitdoor.amessage.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: SettingsRepository) : ViewModel() {

  fun isExperimentsUIEnabled(): Flow<Boolean> = repository.experimentsUIEnabled()

  fun enableExperimentsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setExperimentsUiEnabled()
  }

  fun disableExperimentsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setExperimentsUiDisabled()
  }

  fun isDeveloperSettingsUIEnabled(): Flow<Boolean> = repository.developerSettingsUIEnabled()

  fun enableDeveloperSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setDeveloperSettingsEnabled()
  }

  fun disableDeveloperSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setDeveloperSettingsDisabled()
  }

  fun isEmployeeSettingsUIEnabled(): Flow<Boolean> = repository.employeeSettingsUIEnabled()

  fun enableEmployeeSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setEmployeeSettingsEnabled()
  }

  fun disableEmployeeSettingsUI() = viewModelScope.launch(Dispatchers.IO) {
    repository.setEmployeeSettingsDisabled()
  }

}