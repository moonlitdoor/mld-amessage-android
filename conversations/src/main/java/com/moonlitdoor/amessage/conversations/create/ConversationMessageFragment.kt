//package com.moonlitdoor.amessage.conversations.create
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.moonlitdoor.amessage.conversations.ConversationsDI
//import com.moonlitdoor.amessage.conversations.databinding.FragmentConversationMessageBinding
//import com.stepstone.stepper.Step
//import com.stepstone.stepper.VerificationError
//import javax.inject.Inject
//
//class ConversationMessageFragment : androidx.fragment.app.Fragment(), Step {
//
//  @Inject
//  lateinit var viewModel: ConversationCreateViewModel
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    ConversationsDI.get(requireActivity()).inject(this)
//  }
//
//  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = FragmentConversationMessageBinding.inflate(inflater, container, false).root
//
//  override fun onSelected() {
//  }
//
//  override fun verifyStep(): VerificationError? = null
//
//  override fun onError(error: VerificationError) {}
//}