package com.moonlitdoor.amessage.domain.dao

import android.content.Context
import android.content.SharedPreferences
import com.moonlitdoor.amessage.constants.Constants
import com.moonlitdoor.shared.preference.live.data.liveData


class WindowsDao(context: Context, preferences: SharedPreferences) {

  val count = preferences.liveData(Constants.Keys.SCREENS, Constants.Keys.Defaults.SCREENS)

}