package com.moonlitdoor.amessage.navigation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.preference.PreferenceManager
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.constants.Constants


class NavigationActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    this.setTheme(
      when (PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.Keys.THEME, Constants.Keys.Defaults.THEME)) {
        Constants.Values.Theme.DARK -> R.style.AmTheme_Dark
        Constants.Values.Theme.MONSTER -> R.style.AmTheme_Monster
        Constants.Values.Theme.DEEP -> R.style.AmTheme_Deep
        Constants.Values.Theme.COOL -> R.style.AmTheme_Cool
        else -> R.style.AmTheme
      }
    )
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_navigation)
    PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
  }

  override fun onSupportNavigateUp() = findNavController(this, R.id.fragment).navigateUp()

  override fun onDestroy() {
    super.onDestroy()
    PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
  }

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
    when (key) {
      getString(R.string.preference_theme) -> recreate()
    }
  }

  companion object {
    fun start(context: Context) = context.startActivity(Intent(context, NavigationActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK))
  }
}
