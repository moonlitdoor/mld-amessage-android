package com.moonlitdoor.amessage.conversation

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.WindowsRepository

class ConversationViewModel(conversationRepository: ConversationRepository, profileRepository: ProfileRepository, windowsRepository: WindowsRepository) : ViewModel() {

  val handle = profileRepository.handle

  val windowsCount = windowsRepository.windowsCount

  val conversations = conversationRepository.conversations
}