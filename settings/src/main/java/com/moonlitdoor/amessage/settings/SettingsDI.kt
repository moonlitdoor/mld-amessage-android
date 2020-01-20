package com.moonlitdoor.amessage.settings

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.moonlitdoor.amessage.domain.DomainDI
import com.moonlitdoor.amessage.experiments.ExperimentsDI
import com.moonlitdoor.amessage.experiments.ui.ExperimentsUiDI
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
  modules = [SettingsDI.SettingsModule::class],
  dependencies = [
    DomainDI::class,
    ExperimentsDI::class,
    ExperimentsUiDI::class
  ]
)
interface SettingsDI {

  fun inject(fragment: PreferencesFragment)

  @Module
  class SettingsModule(private var context: Context) {

    @Provides
    fun providesSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  }

  companion object {

    private var component: SettingsDI? = null

    @Synchronized
    fun init(context: Context): SettingsDI = component ?: DaggerSettingsDI.builder()
      .domainDI(DomainDI.init(context))
      .experimentsDI(ExperimentsDI.init(context))
      .experimentsUiDI(ExperimentsUiDI.init(context))
      .settingsModule(SettingsModule(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): SettingsDI = component ?: run { throw Exception("Not Initialized") }

  }

}
