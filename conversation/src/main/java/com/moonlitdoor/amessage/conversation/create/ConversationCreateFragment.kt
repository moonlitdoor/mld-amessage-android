package com.moonlitdoor.amessage.conversation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.moonlitdoor.amessage.conversation.R
import com.moonlitdoor.amessage.conversation.components.StepperAdapter
import com.moonlitdoor.amessage.conversation.components.StepperListener
import com.moonlitdoor.amessage.conversation.databinding.FragmentConversationCreateBinding
import com.moonlitdoor.amessage.extensions.ignore
import com.stepstone.stepper.VerificationError
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConversationCreateFragment : androidx.fragment.app.Fragment(), StepperListener {

  private val viewModel: ConversationCreateViewModel by sharedViewModel()
  private lateinit var binding: FragmentConversationCreateBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentConversationCreateBinding>(inflater, R.layout.fragment_conversation_create, container, false).also {
      viewModel.initConversation()
      binding = it
      setupWithNavController(it.toolbar, findNavController(this))
      activity?.let { act ->
        fragmentManager?.let { fm ->
          it.stepperLayout.also { stepper ->
            stepper.setListener(this)
            stepper.adapter = StepperAdapter(fm, act, ConversationParticipantsFragment(), ConversationTitleFragment(), ConversationTopicFragment(), ConversationMessageFragment())
          }
        }
      }
    }.root

  override fun onCompleted(completeButton: View?) {
    viewModel.createConversation()
    findNavController(this).popBackStack()
  }

  override fun onError(verificationError: VerificationError?) = verificationError?.let {
    com.google.android.material.snackbar.Snackbar.make(binding.root, verificationError.errorMessage, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show()
  }.ignore()

}