package com.moonlitdoor.amessage.conversations

import android.content.Context
import com.moonlitdoor.amessage.conversations.create.ConversationCreateFragment
import com.moonlitdoor.amessage.conversations.create.ConversationMessageFragment
import com.moonlitdoor.amessage.conversations.create.ConversationParticipantsFragment
import com.moonlitdoor.amessage.conversations.create.ConversationTitleFragment
import com.moonlitdoor.amessage.conversations.create.ConversationTopicFragment
import com.moonlitdoor.amessage.domain.DomainDI
import dagger.Component
import dagger.Module

@Component(
  modules = [ConversationsDI.ConnectionsModule::class],
  dependencies = [
    DomainDI::class]
)
interface ConversationsDI {

  fun inject(fragment: ConversationsFragment)
  fun inject(fragment: ConversationCreateFragment)
  fun inject(fragment: ConversationMessageFragment)
  fun inject(fragment: ConversationParticipantsFragment)
  fun inject(fragment: ConversationTitleFragment)
  fun inject(fragment: ConversationTopicFragment)

  @Module
  class ConnectionsModule

  companion object {

    private var component: ConversationsDI? = null

    @Synchronized
    fun init(context: Context): ConversationsDI = component ?: DaggerConversationsDI.builder()
      .domainDI(DomainDI.init(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): ConversationsDI = component ?: run { throw Exception("Not Initialized") }

  }

}
