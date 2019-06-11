package com.moonlitdoor.amessage.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.moonlitdoor.amessage.feedback.databinding.FragmentFeedbackBinding

class FeedbackFragment : androidx.fragment.app.Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentFeedbackBinding>(inflater, R.layout.fragment_feedback, container, false).also {
      it.setLifecycleOwner(this)
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { _ -> findNavController(this).navigateUp() }
    }.root

}