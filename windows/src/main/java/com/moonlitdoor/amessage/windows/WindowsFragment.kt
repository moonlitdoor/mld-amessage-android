package com.moonlitdoor.amessage.windows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.windows.databinding.FragmentWindowsBinding

class WindowsFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentWindowsBinding.inflate(inflater, container, false).also {
      it.lifecycleOwner = this
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { _ -> findNavController(this).navigateUp() }
    }.root

}
