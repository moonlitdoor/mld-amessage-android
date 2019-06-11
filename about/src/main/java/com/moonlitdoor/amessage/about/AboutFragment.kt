package com.moonlitdoor.amessage.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.about.databinding.FragmentAboutBinding
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter

class AboutFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentAboutBinding>(inflater, R.layout.fragment_about, container, false).also {
      it.setLifecycleOwner(this)
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { _ -> findNavController(this).navigateUp() }
      it.tabLayout.setupWithViewPager(it.viewPager)
      activity?.let { act ->
        it.viewPager.adapter = TitledFragmentPagerAdapter(act, listOf(StatisticsFragment(), AcknowledgementsFragment()))
      }
    }.root

}
