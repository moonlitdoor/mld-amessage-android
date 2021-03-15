package com.moonlitdoor.amessage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.moonlitdoor.amessage.theme.MldamessageandroidTheme

//import androidx.preference.PreferenceManager
//import com.moonlitdoor.amessage.constants.Constants


class AMessageActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

  override fun onCreate(savedInstanceState: Bundle?) {
//    this.setTheme(
//      when (PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.Keys.THEME, Constants.Keys.Defaults.THEME)) {
//        Constants.Values.Theme.DARK -> R.style.AmTheme_Dark
//        Constants.Values.Theme.MONSTER -> R.style.AmTheme_Monster
//        Constants.Values.Theme.DEEP -> R.style.AmTheme_Deep
//        Constants.Values.Theme.COOL -> R.style.AmTheme_Cool
//        else -> R.style.AmTheme
//      }
//    )
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_navigation2)
    setContent {
      MldamessageandroidTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Greeting("Android")
        }
      }
    }
//    PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
  }

//  override fun onSupportNavigateUp() = findNavController(this, R.id.fragment).navigateUp()

  override fun onDestroy() {
    super.onDestroy()
//    PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
  }

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
    when (key) {
//      getString(R.string.preference_theme) -> recreate()
    }
  }

  companion object {
    fun start(context: Context) = context.startActivity(Intent(context, AMessageActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK))
  }
}
