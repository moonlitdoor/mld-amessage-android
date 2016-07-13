package com.moonlitdoor.amessage.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.databinding.FragmentConnectBinding

class ConnectFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentConnectBinding>(inflater, R.layout.fragment_connect, container, false).also {
      it.toolbar.setNavigationOnClickListener { _ -> findNavController(this).navigateUp() }
      it.tabLayout.setupWithViewPager(it.viewPager)
      activity?.let { act ->
        it.viewPager.adapter = TitledFragmentPagerAdapter(act, listOf(PendingFragment(), QrFragment(), ScanFragment()))
      }
    }.root

}
