package com.moonlitdoor.amessage.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.help.databinding.FragmentHelpBinding
import com.moonlitdoor.amessage.resources.R

class HelpFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentHelpBinding.inflate(inflater, container, false).also {
      it.lifecycleOwner = this
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { findNavController(this).navigateUp() }
    }.root

}
