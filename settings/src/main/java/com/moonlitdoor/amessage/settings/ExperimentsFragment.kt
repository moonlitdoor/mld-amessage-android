package com.moonlitdoor.amessage.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class ExperimentsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

  override fun onCreatePreferences(bundle: Bundle?, root: String?) {
//    setPreferencesFromResource(R.xml.experiments, root)
    PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this)
  }

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
//    when (key) {
//    }
  }

  companion object {
    fun create(): androidx.fragment.app.Fragment = ExperimentsFragment()
  }

}
