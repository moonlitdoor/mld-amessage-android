package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import com.moonlitdoor.amessage.database.entity.KeyValueEntity
import com.moonlitdoor.amessage.database.projection.DeveloperSettingsEnabledProjection
import com.moonlitdoor.amessage.database.projection.EmployeeSettingsEnabledProjection
import com.moonlitdoor.amessage.database.projection.ExperimentsUiEnabledProjection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface SettingsDao : KeyValueDao {

  fun isExperimentsUiEnabledFlow(): Flow<Boolean> = getFlow(ExperimentsUiEnabledProjection.KEY).map { it.toBoolean() }

  suspend fun isExperimentsUiEnabled(): ExperimentsUiEnabledProjection = ExperimentsUiEnabledProjection(getValue(ExperimentsUiEnabledProjection.KEY).toBoolean())

  suspend fun setExperimentsUi(value: ExperimentsUiEnabledProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  fun isEmployeeSettingsUiEnabledFlow(): Flow<Boolean> = getFlow(EmployeeSettingsEnabledProjection.KEY).map { it.toBoolean() }

  suspend fun isEmployeeSettingsEnabled(): EmployeeSettingsEnabledProjection = EmployeeSettingsEnabledProjection(getValue(EmployeeSettingsEnabledProjection.KEY).toBoolean())

  suspend fun setEmployeeSettings(value: EmployeeSettingsEnabledProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  fun isDeveloperSettingsUIEnabledFlow(): Flow<Boolean> = getFlow(DeveloperSettingsEnabledProjection.KEY).map { it.toBoolean() }

  suspend fun isDeveloperSettingsEnabled(): DeveloperSettingsEnabledProjection = DeveloperSettingsEnabledProjection(getValue(DeveloperSettingsEnabledProjection.KEY).toBoolean())

  suspend fun setDeveloperSettings(value: DeveloperSettingsEnabledProjection): Unit = setKeyValue(KeyValueEntity.from(value))
}
