package com.moonlitdoor.amessage.conversations.create

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.moonlitdoor.amessage.conversations.ConversationsDI
import com.moonlitdoor.amessage.conversations.components.StepperAdapter
import com.moonlitdoor.amessage.conversations.components.StepperListener
import com.moonlitdoor.amessage.conversations.databinding.FragmentConversationCreateBinding
import com.moonlitdoor.amessage.extensions.ignore
import com.stepstone.stepper.VerificationError
import javax.inject.Inject

class ConversationCreateFragment : androidx.fragment.app.Fragment(), StepperListener {

  @Inject
  lateinit var viewModel: ConversationCreateViewModel
  private lateinit var binding: FragmentConversationCreateBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ConversationsDI.get(requireActivity()).inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentConversationCreateBinding.inflate(inflater, container, false).also {
      viewModel.initConversation()
      binding = it
      setupWithNavController(it.toolbar, findNavController(this))
      activity?.let { act ->
        parentFragmentManager.let { fm ->
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

  @SuppressLint("WrongConstant")
  override fun onError(verificationError: VerificationError?) = verificationError?.let {
    Snackbar.make(binding.root, verificationError.errorMessage, Snackbar.LENGTH_LONG).show()
  }.ignore()

}