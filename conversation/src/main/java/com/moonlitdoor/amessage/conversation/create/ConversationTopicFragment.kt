package com.moonlitdoor.amessage.conversation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.moonlitdoor.amessage.conversation.R
import com.moonlitdoor.amessage.conversation.databinding.FragmentConversationTopicBinding
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConversationTopicFragment : androidx.fragment.app.Fragment(), Step {

  private val viewModel: ConversationCreateViewModel by sharedViewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentConversationTopicBinding>(inflater, R.layout.fragment_conversation_topic, container, false).also {
      it.viewModel = viewModel
    }.root

  override fun onSelected() {
  }

  override fun verifyStep(): VerificationError? = null

  override fun onError(error: VerificationError) {}
}