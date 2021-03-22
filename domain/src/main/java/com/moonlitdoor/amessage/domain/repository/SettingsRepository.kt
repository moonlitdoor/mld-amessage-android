package com.moonlitdoor.amessage.domain.repository

//import com.moonlitdoor.amessage.database.dao.SettingsDao
import javax.inject.Inject

class SettingsRepository @Inject constructor(/*private val dao: SettingsDao*/) {

  fun getExperimentsUiEnabled() = Unit//dao.getExperimentsUiEnabled()

  fun setExperimentsUiEnabled() = Unit//dao.setExperimentsUiEnabled()

  fun getDeveloperSettingsEnabled() = Unit//dao.getDeveloperSettingsUiEnabled()

  fun setDeveloperSettingsEnabled() = Unit//dao.setDeveloperSettingsEnabled()

  fun getEmployeeSettingsEnabled() = Unit//dao.getEmployeeSettingsEnabled()

  fun setEmployeeSettingsEnabled() = Unit//dao.setEmployeeSettingsEnabled()

}