package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.SettingsDao
import com.moonlitdoor.amessage.database.projection.DeveloperSettingsEnabledProjection
import com.moonlitdoor.amessage.database.projection.EmployeeSettingsEnabledProjection
import com.moonlitdoor.amessage.database.projection.ExperimentsUiEnabledProjection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val dao: SettingsDao) {

  fun experimentsUIEnabled(): Flow<Boolean> = dao.isExperimentsUiEnabledFlow()

  suspend fun isExperimentsUiEnabled(): Boolean = dao.isExperimentsUiEnabled().value

  suspend fun setExperimentsUiEnabled(): Unit = dao.setExperimentsUi(ExperimentsUiEnabledProjection(true))

  suspend fun setExperimentsUiDisabled(): Unit = dao.setExperimentsUi(ExperimentsUiEnabledProjection(false))

  fun developerSettingsUIEnabled(): Flow<Boolean> = dao.isDeveloperSettingsUIEnabledFlow()

  suspend fun isDeveloperSettingsEnabled(): Boolean = dao.isDeveloperSettingsEnabled().value

  suspend fun setDeveloperSettingsEnabled(): Unit = dao.setDeveloperSettings(DeveloperSettingsEnabledProjection(true))

  suspend fun setDeveloperSettingsDisabled(): Unit = dao.setDeveloperSettings(DeveloperSettingsEnabledProjection(false))

  fun employeeSettingsUIEnabled(): Flow<Boolean> = dao.isEmployeeSettingsUiEnabledFlow()

  suspend fun isEmployeeSettingsEnabled(): Boolean = dao.isEmployeeSettingsEnabled().value

  suspend fun setEmployeeSettingsEnabled(): Unit = dao.setEmployeeSettings(EmployeeSettingsEnabledProjection(true))

  suspend fun setEmployeeSettingsDisabled(): Unit = dao.setEmployeeSettings(EmployeeSettingsEnabledProjection(false))

}
