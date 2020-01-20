package com.moonlitdoor.amessage.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moonlitdoor.amessage.connect.databinding.FragmentQrBinding
import javax.inject.Inject

class QrFragment : Fragment() {

  @Inject
  lateinit var viewModel: ConnectViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ConnectDI.get().inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = FragmentQrBinding.inflate(inflater, container, false).also {
    it.lifecycleOwner = this
    it.viewModel = viewModel
  }.root

  companion object {
    val titleId = R.string.connect_qr_title
  }

}
