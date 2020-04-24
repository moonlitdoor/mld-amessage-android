package com.moonlitdoor.amessage.conversations

import android.app.Activity
import com.moonlitdoor.amessage.conversations.create.ConversationCreateFragment
import com.moonlitdoor.amessage.conversations.create.ConversationMessageFragment
import com.moonlitdoor.amessage.conversations.create.ConversationParticipantsFragment
import com.moonlitdoor.amessage.conversations.create.ConversationTitleFragment
import com.moonlitdoor.amessage.conversations.create.ConversationTopicFragment
import dagger.Module
import dagger.Subcomponent

@Subcomponent(
  modules = [ConversationsDI.ConnectionsModule::class]
)
interface ConversationsDI {

  fun inject(fragment: ConversationsFragment)
  fun inject(fragment: ConversationCreateFragment)
  fun inject(fragment: ConversationMessageFragment)
  fun inject(fragment: ConversationParticipantsFragment)
  fun inject(fragment: ConversationTitleFragment)
  fun inject(fragment: ConversationTopicFragment)

  interface ConversationsDIProvider {
    fun provideConversationsDI(): ConversationsDI
  }

  @Module
  class ConnectionsModule

  @Subcomponent.Factory
  interface Factory {
    fun create(): ConversationsDI
  }

  companion object {

    @Synchronized
    fun get(activity: Activity): ConversationsDI = (activity.application as ConversationsDIProvider).provideConversationsDI()

  }

}
