package com.moonlitdoor.amessage.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.about.databinding.FragmentAboutBinding
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.resources.R

@Keep
class AboutFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AboutDI.init().inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentAboutBinding.inflate(inflater, container, false).also {
      it.lifecycleOwner = this
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { findNavController(this).navigateUp() }
      it.tabLayout.setupWithViewPager(it.viewPager)
      activity?.let { act ->
        it.viewPager.adapter = TitledFragmentPagerAdapter(act, listOf(StatisticsFragment(), AcknowledgementsFragment()))
      }
    }.root

}
