package com.moonlitdoor.amessage.conversation.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.domain.model.SelectableConnection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.extensions.map

class ConversationCreateViewModel(private val conversationRepository: ConversationRepository, connectionRepository: ConnectionRepository, private val factory: ConversationFactory) : ViewModel() {

  fun initConversation() {
    conversation = factory.getInstance()
  }

  fun createConversation() {
    conversation?.let {
      conversationRepository.create(it)
    }
  }

  val connections: LiveData<List<SelectableConnection>> = connectionRepository.getConnectedConnections().map {
    it.map {
      SelectableConnection.from(it)
    }
  }

  var conversation: Conversation? = null

}