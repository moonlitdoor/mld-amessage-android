package com.moonlitdoor.amessage.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.databinding.FragmentHelpBinding

class HelpFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentHelpBinding>(inflater, R.layout.fragment_help, container, false).also {
      it.setLifecycleOwner(this)
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { findNavController(this).navigateUp() }
    }.root

}
