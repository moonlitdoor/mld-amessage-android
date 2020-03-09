package com.moonlitdoor.amessage.conversations

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.WindowsRepository
import javax.inject.Inject

class ConversationViewModel @Inject constructor(conversationRepository: ConversationRepository, profileRepository: ProfileRepository, windowsRepository: WindowsRepository) :
  ViewModel() {

  val handle = profileRepository.handle

  val windowsCount = windowsRepository.windowsCount

  val conversations = conversationRepository.conversations
}