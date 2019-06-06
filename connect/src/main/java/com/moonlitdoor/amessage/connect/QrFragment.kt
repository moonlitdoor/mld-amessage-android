package com.moonlitdoor.amessage.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.connect.databinding.FragmentQrBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QrFragment : TitledFragmentPagerAdapter.TitledFragment() {

  private val viewModel by viewModel<ConnectViewModel>()

  override fun getTitleId() = R.string.connect_qr_title

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = DataBindingUtil.inflate<FragmentQrBinding>(inflater, R.layout.fragment_qr, container, false).also {
    it.lifecycleOwner = this
    it.viewModel = viewModel
  }.root

}
