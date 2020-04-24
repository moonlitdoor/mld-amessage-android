package com.moonlitdoor.amessage.settings

import android.app.Activity
import dagger.Module
import dagger.Subcomponent

@Subcomponent(
  modules = [SettingsDI.SettingsModule::class]
)
interface SettingsDI {

  fun inject(fragment: PreferencesFragment)

  interface SettingsDIProvider {
    fun provideSettingsDI(): SettingsDI
  }

  @Module
  class SettingsModule

  @Subcomponent.Factory
  interface Factory {
    fun create(): SettingsDI
  }

  companion object {

    @Synchronized
    fun get(activity: Activity): SettingsDI = (activity.application as SettingsDIProvider).provideSettingsDI()

  }

}
