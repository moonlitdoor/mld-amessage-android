package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.SettingsDao
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val dao: SettingsDao) {

  fun getExperimentsUiEnabled() = dao.getExperimentsUiEnabled()

  fun setExperimentsUiEnabled() = dao.setExperimentsUiEnabled()

  fun getDeveloperSettingsEnabled() = dao.getDeveloperSettingsUiEnabled()

  fun setDeveloperSettingsEnabled() = dao.setDeveloperSettingsEnabled()

  fun getEmployeeSettingsEnabled() = dao.getEmployeeSettingsEnabled()

  fun setEmployeeSettingsEnabled() = dao.setEmployeeSettingsEnabled()

}