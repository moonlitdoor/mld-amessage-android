package com.moonlitdoor.amessage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.settings.databinding.FragmentSettingsBinding

class SettingsFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentSettingsBinding>(inflater, R.layout.fragment_settings, container, false).also {
      it.lifecycleOwner = this
      it.toolbar.setNavigationOnClickListener { _ -> findNavController(this).navigateUp() }
      SettingsFragmentArgs.fromBundle(arguments!!).apply {
        it.toolbar.title = if (title == "null") getString(R.string.title_activity_settings) else title
        fragmentManager?.beginTransaction()?.add(R.id.settings_container, if (experiments) ExperimentsFragment.create() else PreferencesFragment.create(this))?.commit()
      }
    }.root

}
