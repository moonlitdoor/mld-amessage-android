package com.moonlitdoor.amessage.domain.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.constants.Constants
import com.moonlitdoor.shared.preference.live.data.liveData

class ThemeRepository(preferences: SharedPreferences) {

  val theme: LiveData<String> = preferences.liveData(Constants.Keys.THEME, "1")

}