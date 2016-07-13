package com.moonlitdoor.amessage.conversation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.databinding.FragmentConversationTitleBinding
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ConversationTitleFragment : androidx.fragment.app.Fragment(), Step {

  private val viewModel: ConversationCreateViewModel by sharedViewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentConversationTitleBinding>(inflater, R.layout.fragment_conversation_title, container, false).also {
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