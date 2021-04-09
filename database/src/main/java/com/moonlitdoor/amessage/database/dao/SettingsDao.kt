package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import com.moonlitdoor.amessage.database.entity.KeyValueEntity
import com.moonlitdoor.amessage.database.projection.DeveloperSettingsEnabledProjection
import com.moonlitdoor.amessage.database.projection.EmployeeSettingsEnabledProjection
import com.moonlitdoor.amessage.database.projection.ExperimentsUiEnabledProjection

@Dao
interface SettingsDao : KeyValueDao {

  suspend fun isExperimentsUiEnabled(): ExperimentsUiEnabledProjection = ExperimentsUiEnabledProjection(getValue(ExperimentsUiEnabledProjection.KEY).toBoolean())

  suspend fun setExperimentsUi(value: ExperimentsUiEnabledProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  suspend fun isEmployeeSettingsEnabled(): EmployeeSettingsEnabledProjection = EmployeeSettingsEnabledProjection(getValue(EmployeeSettingsEnabledProjection.KEY).toBoolean())

  suspend fun setEmployeeSettings(value: EmployeeSettingsEnabledProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  suspend fun isDeveloperSettingsEnabled(): DeveloperSettingsEnabledProjection = DeveloperSettingsEnabledProjection(getValue(DeveloperSettingsEnabledProjection.KEY).toBoolean())

  suspend fun setDeveloperSettings(value: DeveloperSettingsEnabledProjection): Unit = setKeyValue(KeyValueEntity.from(value))

}
