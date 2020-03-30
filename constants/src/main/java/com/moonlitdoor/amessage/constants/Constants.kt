package com.moonlitdoor.amessage.constants

import android.content.res.Resources
import javax.inject.Inject

object Constants {

  class Res {

    @Inject
    lateinit var resources: Resources

  }

  val res: Res = ConstantsDI.get().inject(Res())

  const val EXPERIMENTS = "YfZO7iEcaej2mq6ZZdMyfPlhJ4C3i1XCtYH9Sk7bEqrKrRZ0vKZhi5nvXnckc983tpyw8lfRCUPUAzSWBqxhAV9g53wgTbsftnAz9YPNfzZ5dZs9SUAXyygZ"
  const val DEVELOPER_SETTINGS = "asiV4AvdRTnwHL69SmdxDN3UUbnebp357OWlHC4OjlhN2m4ScJ1yfI4xYMYA7SPO71a3Ja96ag0f1WUYqvqFKBYxn24CRt7N0WGrg2olHsu6TGPlthImfL3y"
  const val EMPLOYEE_SETTINGS = "P06mqli1A8ZpJc7pTf9wcl2xnX87JSxRzz9pQuAJKjIu0MuNrEN2eqlZoi4Iip7MvdACLrXUA9nZXa4alNKWI9bnHxRhlxso2HEqtksj2UpBZuQxdw5pVuVI"

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

    val SCREENS: String = res.resources.getString(R.string.experiment_screens_count)
    val THEME: String = res.resources.getString(R.string.preference_theme)
    val STARTING_LOCATION: String = res.resources.getString(R.string.preference_starting_location)
    val WHATS_NEW_PREFERENCE: String = res.resources.getString(R.string.whats_new_preference_button)
    val DEVELOPER_SETTINGS: String = res.resources.getString(R.string.preference_developer_settings)
    val DEVELOPER_SETTINGS_VISIBLE: String = res.resources.getString(R.string.preference_developer_settings_visible)
    val EMPLOYEE_SETTINGS: String = res.resources.getString(R.string.preference_employee_settings)
    val EMPLOYEE_SETTINGS_VISIBLE: String = res.resources.getString(R.string.preference_employee_settings_visible)
    val EXPERIMENTS_SETTINGS: String = res.resources.getString(R.string.preference_experiments_settings)
    val EXPERIMENTS_SETTINGS_VISIBLE: String = res.resources.getString(R.string.preference_experiments_settings_visible)

    object Defaults {
      val THEME: String = res.resources.getString(R.string.preference_theme_default)
      const val SCREENS: Int = 5
    }
  }

  object Values {
    object Theme {
      val DARK: String = res.resources.getString(R.string.preference_theme_dark)
      val MONSTER: String = res.resources.getString(R.string.preference_theme_monster)
      val DEEP: String = res.resources.getString(R.string.preference_theme_deep)
      val COOL: String = res.resources.getString(R.string.preference_theme_cool)
    }
  }

}
