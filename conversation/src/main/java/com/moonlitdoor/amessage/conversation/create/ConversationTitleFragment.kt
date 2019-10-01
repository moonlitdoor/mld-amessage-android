package com.moonlitdoor.amessage.conversation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moonlitdoor.amessage.conversation.databinding.FragmentConversationTitleBinding
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConversationTitleFragment : androidx.fragment.app.Fragment(), Step {

  private val viewModel: ConversationCreateViewModel by sharedViewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentConversationTitleBinding.inflate(inflater, container, false).also {
      it.conversation = viewModel.conversation
    }.root

  override fun onSelected() {
  }

  override fun verifyStep(): VerificationError? {
    viewModel.conversation
    return null
  }

  override fun onError(error: VerificationError) {}
}