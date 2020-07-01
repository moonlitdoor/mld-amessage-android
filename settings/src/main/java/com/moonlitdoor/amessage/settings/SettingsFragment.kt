package com.moonlitdoor.amessage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.settings.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentSettingsBinding.inflate(inflater, container, false).also {
      it.lifecycleOwner = this
//      it.toolbar.setOnLongClickListener { Toast.makeText(requireContext(), "onLongClickListener", Toast.LENGTH_SHORT).show(); true }
//      it.toolbar.setOnClickListener { Toast.makeText(requireContext(), "onClickListener", Toast.LENGTH_SHORT).show() }
      it.toolbar.setNavigationOnClickListener { findNavController(this).navigateUp() }
      SettingsFragmentArgs.fromBundle(requireArguments()).apply {
        it.toolbar.title = title ?: getString(R.string.title_activity_settings)
        parentFragmentManager.beginTransaction().add(R.id.settings_container, PreferencesFragment.create(this)).commit()
      }
    }.root

}
