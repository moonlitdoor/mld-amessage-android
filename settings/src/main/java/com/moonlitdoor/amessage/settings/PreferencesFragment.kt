package com.moonlitdoor.amessage.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.preference.*
import com.google.android.material.snackbar.Snackbar
import com.moonlitdoor.amessage.constants.Constants
import org.koin.android.ext.android.inject

class PreferencesFragment : PreferenceFragmentCompat(), PreferenceFragmentCompat.OnPreferenceStartScreenCallback, SharedPreferences.OnSharedPreferenceChangeListener {

  private val settings: SharedPreferences by inject()

  override fun onCreatePreferences(bundle: Bundle?, root: String?) {
    setPreferencesFromResource(R.xml.preferences, if (root == "null") null else root)
    settings.registerOnSharedPreferenceChangeListener(this)
    findPreference<Preference>(Constants.Keys.WHATS_NEW_PREFERENCE)?.setOnPreferenceClickListener {
      PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(Constants.SharedPreferences.VERSION_CODE, 0).apply()
      Snackbar.make(view!!, R.string.whats_new_preference_toast, Snackbar.LENGTH_SHORT).show()
      true
    }
    findPreference<ListPreference>(Constants.Keys.THEME)?.summary = resources.getStringArray(R.array.preference_theme_list_titles)[Integer.parseInt(settings.getString(Constants.Keys.THEME, null) ?: "0")]
    findPreference<ListPreference>(Constants.Keys.STARTING_LOCATION)?.summary = resources.getStringArray(R.array.preference_starting_location_list_titles)[Integer.parseInt(settings.getString(Constants.Keys.STARTING_LOCATION, null) ?: "")]
    findPreference<Preference>(Constants.Keys.EXPERIMENTS)?.let {
      //TODO      it.isVisible = false
      it.setOnPreferenceClickListener { _ ->
        findNavController(this).navigate(R.id.experiments)
        true
      }
    }
  }

  override fun onDetach() {
    super.onDetach()
    settings.unregisterOnSharedPreferenceChangeListener(this)
  }

  override fun getCallbackFragment(): androidx.fragment.app.Fragment {
    return this
  }

  override fun onPreferenceStartScreen(caller: PreferenceFragmentCompat, pref: PreferenceScreen): Boolean {
    findNavController(this).navigate(SettingsNavigationDirections.settings().setTitle(pref.title.toString()).setRoot(pref.key))
    return true
  }

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
    when (key) {
      Constants.Keys.STARTING_LOCATION -> findPreference<ListPreference>(key)?.summary = resources.getStringArray(R.array.preference_starting_location_list_titles)[Integer.parseInt(sharedPreferences.getString(key, null) ?: "")]
      Constants.Keys.THEME -> findPreference<ListPreference>(key)?.summary = resources.getStringArray(R.array.preference_theme_list_titles)[Integer.parseInt(sharedPreferences.getString(key, null) ?: "")]
    }
  }

  companion object {
    fun create(args: SettingsFragmentArgs): androidx.fragment.app.Fragment = PreferencesFragment().apply {
      arguments = Bundle().also {
        it.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, args.root)
      }
    }
  }
}