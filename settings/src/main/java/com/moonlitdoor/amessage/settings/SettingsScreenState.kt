package com.moonlitdoor.amessage.settings

sealed class SettingsScreenState {
  object Loading : SettingsScreenState()
  class Data(val experiments: Boolean, val developer: Boolean, val employee: Boolean) : SettingsScreenState()
}
