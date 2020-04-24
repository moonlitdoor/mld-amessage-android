package com.moonlitdoor.amessage.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.google.android.material.snackbar.Snackbar
import com.moonlitdoor.amessage.constants.Constants
import javax.inject.Inject

class PreferencesFragment : PreferenceFragmentCompat(), PreferenceFragmentCompat.OnPreferenceStartScreenCallback, SharedPreferences.OnSharedPreferenceChangeListener {

  @Inject
  lateinit var sharedPreferences: SharedPreferences

  @Inject
  lateinit var viewModel: SettingsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    SettingsDI.get(requireActivity()).inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreatePreferences(bundle: Bundle?, root: String?) {
    setPreferencesFromResource(R.xml.preferences, if (root == "null") null else root)
    sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    findPreference<Preference>(Constants.Keys.WHATS_NEW_PREFERENCE)?.setOnPreferenceClickListener {
      sharedPreferences.edit { putInt(Constants.SharedPreferences.VERSION_CODE, 0) }
      Snackbar.make(requireView(), R.string.whats_new_preference_toast, Snackbar.LENGTH_SHORT).show()
      true
    }
    findPreference<ListPreference>(Constants.Keys.THEME)?.summary = resources.getStringArray(R.array.preference_theme_list_titles)[Integer.parseInt(sharedPreferences.getString(Constants.Keys.THEME, null) ?: "0")]
    findPreference<ListPreference>(Constants.Keys.STARTING_LOCATION)?.summary =
      resources.getStringArray(R.array.preference_starting_location_list_titles)[Integer.parseInt(sharedPreferences.getString(Constants.Keys.STARTING_LOCATION, null) ?: "")]
    findPreference<Preference>(Constants.Keys.EMPLOYEE_SETTINGS)?.let {
      it.isVisible = viewModel.employeeSettingsEnabled || root == Constants.Keys.DEVELOPER_SETTINGS
    }
//    findPreference<SwitchPreference>(Constants.Keys.EMPLOYEE_SETTINGS_VISIBLE)?.setOnPreferenceChangeListener { _, newValue ->
//      sharedPreferences.edit{ putBoolean(Constants.Keys.EMPLOYEE_SETTINGS, newValue as Boolean)}
//      true
//    }
    findPreference<Preference>(Constants.Keys.DEVELOPER_SETTINGS)?.let {
      it.isVisible = viewModel.developerSettingsEnabled || root == Constants.Keys.DEVELOPER_SETTINGS || BuildConfig.DEBUG
    }
//    findPreference<SwitchPreference>(Constants.Keys.DEVELOPER_SETTINGS_VISIBLE)?.setOnPreferenceChangeListener { _, newValue ->
//      sharedPreferences.edit{ putBoolean(Constants.Keys.DEVELOPER_SETTINGS, newValue as Boolean)}
//      true
//    }
    findPreference<Preference>(Constants.Keys.EXPERIMENTS_SETTINGS)?.let {
      it.isVisible = viewModel.experimentUiEnabled || root == Constants.Keys.DEVELOPER_SETTINGS
      it.setOnPreferenceClickListener {
        findNavController(this).navigate(R.id.experiments)
        true
      }
    }
//    findPreference<SwitchPreference>(Constants.Keys.EXPERIMENTS_SETTINGS_VISIBLE)?.setOnPreferenceChangeListener { _, newValue ->
//      sharedPreferences.edit{ putBoolean(Constants.Keys.EXPERIMENTS_SETTINGS, newValue as Boolean)}
//      true
//    }

  }

  override fun onDetach() {
    super.onDetach()
    sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
  }

  override fun getCallbackFragment(): androidx.fragment.app.Fragment {
    return this
  }

  override fun onPreferenceStartScreen(caller: PreferenceFragmentCompat, pref: PreferenceScreen): Boolean {
    findNavController(this).navigate(SettingsNavigationDirections.settings().setTitle(pref.title.toString()).setRoot(pref.key))
    return true
  }

  override fun onSharedPreferenceChanged(preferences: SharedPreferences, key: String) {
    when (key) {
      Constants.Keys.STARTING_LOCATION -> findPreference<ListPreference>(key)?.summary =
        resources.getStringArray(R.array.preference_starting_location_list_titles)[Integer.parseInt(preferences.getString(key, null) ?: "")]
      Constants.Keys.THEME -> findPreference<ListPreference>(key)?.summary =
        resources.getStringArray(R.array.preference_theme_list_titles)[Integer.parseInt(preferences.getString(key, null) ?: "")]
    }
  }

  companion object {
    fun create(args: SettingsFragmentArgs): androidx.fragment.app.Fragment = PreferencesFragment().apply {
      arguments = Bundle().also {
        it.putString(ARG_PREFERENCE_ROOT, args.root)
      }
    }
  }
}