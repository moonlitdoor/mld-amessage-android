package com.moonlitdoor.amessage.constants

import android.content.res.Resources
import org.koin.core.KoinComponent
import org.koin.core.inject

object Constants : KoinComponent {

  private val resources by inject<Resources>()

  const val EXPERIMENTS = "YfZO7iEcaej2mq6ZZdMyfPlhJ4C3i1XCtYH9Sk7bEqrKrRZ0vKZhi5nvXnckc983tpyw8lfRCUPUAzSWBqxhAV9g53wgTbsftnAz9YPNfzZ5dZs9SUAXyygZ"

  const val BASE_URL_FIREBASE = "https://fcm.googleapis.com"

  object SharedPreferences {

    const val VERSION_CODE = "com.moonlitdoor.amessage.version_code"
    const val HANDLE = "com.moonlitdoor.amessage.domain.dao.ProfileDao.HANDLE"
    const val TOKEN = "com.moonlitdoor.amessage.domain.dao.ProfileDao.TOKEN"
    const val ID = "com.moonlitdoor.amessage.domain.dao.ProfileDao.ID"
    const val PASSWORD = "com.moonlitdoor.amessage.domain.dao.ProfileDao.PASSWORD"
    const val SALT = "com.moonlitdoor.amessage.domain.dao.ProfileDao.SALT"
  }

  object Keys {

    val SCREENS: String = resources.getString(R.string.experiment_screens_count)
    val THEME: String = resources.getString(R.string.preference_theme)
    val STARTING_LOCATION: String = resources.getString(R.string.preference_starting_location)
    val WHATS_NEW_PREFERENCE: String = resources.getString(R.string.whats_new_preference_button)
    val EXPERIMENTS: String = resources.getString(R.string.experiments_preference_button)

    object Defaults {
      val THEME: String = resources.getString(R.string.preference_theme_default)
      const val SCREENS: Int = 5
    }
  }

  object Values {
    object Theme {
      val DARK: String = resources.getString(R.string.preference_theme_dark)
      val MONSTER: String = resources.getString(R.string.preference_theme_monster)
      val DEEP: String = resources.getString(R.string.preference_theme_deep)
      val COOL: String = resources.getString(R.string.preference_theme_cool)
    }
  }

}
