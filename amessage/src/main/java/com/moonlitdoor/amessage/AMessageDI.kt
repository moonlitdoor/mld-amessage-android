package com.moonlitdoor.amessage

import android.content.Context
import com.moonlitdoor.amessage.connect.ConnectDI
import com.moonlitdoor.amessage.connections.ConnectionsDI
import com.moonlitdoor.amessage.conversations.ConversationsDI
import com.moonlitdoor.amessage.domain.DomainDI
import com.moonlitdoor.amessage.domain.repository.FrequentlyAskedQuestionRepository
import com.moonlitdoor.amessage.settings.SettingsDI
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
  modules = [AMessageDI.AMessageModule::class],
  dependencies = [
    ConnectionsDI::class,
    ConversationsDI::class,
    ConnectDI::class,
    SettingsDI::class,
    DomainDI::class
  ]
)
@AMessageDI.AMessageScope
interface AMessageDI {

  fun frequentlyAskedQuestionRepository(): FrequentlyAskedQuestionRepository

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class AMessageScope

  @Module
  class AMessageModule

  companion object {

    private var component: AMessageDI? = null

    @Synchronized
    fun init(context: Context): AMessageDI = component ?: DaggerAMessageDI.builder()
      .connectionsDI(ConnectionsDI.init(context))
      .conversationsDI(ConversationsDI.init(context))
      .connectDI(ConnectDI.init(context))
      .settingsDI(SettingsDI.init(context))
      .domainDI(DomainDI.init(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): AMessageDI = component ?: run { throw Exception("Not Initialized") }

  }

}
