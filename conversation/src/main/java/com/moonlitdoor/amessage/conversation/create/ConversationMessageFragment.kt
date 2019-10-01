package com.moonlitdoor.amessage.conversation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moonlitdoor.amessage.conversation.databinding.FragmentConversationMessageBinding
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConversationMessageFragment : androidx.fragment.app.Fragment(), Step {

  private val viewModel: ConversationCreateViewModel by sharedViewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentConversationMessageBinding.inflate(inflater, container, false).also {
    }.root

  override fun onSelected() {
  }

  override fun verifyStep(): VerificationError? = null

  override fun onError(error: VerificationError) {}
}