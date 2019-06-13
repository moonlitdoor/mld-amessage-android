package com.moonlitdoor.amessage.conversation.components

import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError

interface StepperListener : StepperLayout.StepperListener {
  override fun onStepSelected(newStepPosition: Int) {}
  override fun onError(verificationError: VerificationError?) {}
  override fun onReturn() {}
}