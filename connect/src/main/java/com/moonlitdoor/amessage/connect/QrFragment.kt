package com.moonlitdoor.amessage.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moonlitdoor.amessage.connect.databinding.FragmentQrBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class QrFragment : Fragment() {

  private val viewModel by sharedViewModel<ConnectViewModel>()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = FragmentQrBinding.inflate(inflater, container, false).also {
    it.lifecycleOwner = this
    it.viewModel = viewModel
  }.root

  companion object {
    val titleId = R.string.connect_qr_title
  }

}
