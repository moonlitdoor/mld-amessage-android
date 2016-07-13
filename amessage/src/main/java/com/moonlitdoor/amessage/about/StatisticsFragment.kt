package com.moonlitdoor.amessage.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.moonlitdoor.amessage.BuildConfig
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.databinding.FragmentStatisticsBinding
import com.moonlitdoor.amessage.extensions.ignore

class StatisticsFragment : TitledFragmentPagerAdapter.TitledFragment(), View.OnClickListener {

  override fun getTitleId() = R.string.about_page_view_statistics

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentStatisticsBinding>(inflater, R.layout.fragment_statistics, container, false).apply {
      handler = this@StatisticsFragment
    }.root

  override fun onClick(view: View) = activity?.let {
    startActivity(
      Intent(
        Intent.ACTION_VIEW, Uri.parse(
          when (view.id) {
            R.id.build -> "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
            R.id.privacy -> getString(R.string.about_privacy_policy_url)
            else -> ""
          }
        )
      ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )
  }.ignore()

}
