package com.moonlitdoor.amessage.domain.dao

import android.content.Context
import android.content.SharedPreferences
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.util.liveData

class WindowsDao(context: Context, preferences: SharedPreferences) {

  val count = preferences.liveData(context.getString(R.string.experiment_screens_count), 5)

}