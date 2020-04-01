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
import javax.inject.Scope

@Component(
  modules = [SettingsDI.SettingsModule::class],
  dependencies = [
    DomainDI::class,
    ExperimentsDI::class,
    ExperimentsUiDI::class
  ]
)
@SettingsDI.SettingsScope
interface SettingsDI {

  fun inject(fragment: PreferencesFragment)

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class SettingsScope

  @Module
  class SettingsModule(private var context: Context) {

    @Provides
    fun providesSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

  }

  @Component.Factory
  interface Factory {
    fun create(domainDI: DomainDI, experimentsDI: ExperimentsDI, experimentsUiDI: ExperimentsUiDI, settingsModule: SettingsModule): SettingsDI
  }

  companion object {

    private var component: SettingsDI? = null

    @Synchronized
    fun init(context: Context): SettingsDI = component ?: DaggerSettingsDI.factory().create(
      domainDI = DomainDI.init(context),
      experimentsDI = ExperimentsDI.init(context),
      experimentsUiDI = ExperimentsUiDI.init(),
      settingsModule = SettingsModule(context)
    ).also {
      component = it
    }

    @Synchronized
    fun get(): SettingsDI = component ?: run { throw Exception("Not Initialized") }

  }

}
