package com.moonlitdoor.amessage.database.dao

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.constants.Constants
import com.moonlitdoor.shared.preference.live.data.liveData

class ThemeDao(preferences: SharedPreferences) {

  val theme: LiveData<String> = preferences.liveData(Constants.Keys.THEME, "1")
}