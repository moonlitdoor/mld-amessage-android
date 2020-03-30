package com.moonlitdoor.amessage.settings

import com.moonlitdoor.amessage.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val repository: SettingsRepository) {

  val experimentUiEnabled
    get() = repository.getExperimentsUiEnabled()

  val developerSettingsEnabled
    get() = repository.getDeveloperSettingsEnabled()

  val employeeSettingsEnabled
    get() = repository.getEmployeeSettingsEnabled()

}