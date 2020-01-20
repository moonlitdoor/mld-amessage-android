package com.moonlitdoor.amessage.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.feedback.databinding.FragmentFeedbackBinding
import com.moonlitdoor.amessage.resources.R

class FeedbackFragment : androidx.fragment.app.Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    FeedbackDI.init().inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentFeedbackBinding.inflate(inflater, container, false).also {
      it.lifecycleOwner = this
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { _ -> findNavController(this).navigateUp() }
    }.root

}
