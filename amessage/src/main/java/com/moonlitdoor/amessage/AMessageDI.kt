package com.moonlitdoor.amessage

import android.content.Context
import com.moonlitdoor.amessage.connect.ConnectDI
import com.moonlitdoor.amessage.connections.ConnectionsDI
import com.moonlitdoor.amessage.conversations.ConversationsDI
import com.moonlitdoor.amessage.settings.SettingsDI
import dagger.Component
import dagger.Module

@Component(
  modules = [AMessageDI.AmessageModule::class],
  dependencies = [
    ConnectionsDI::class,
    ConversationsDI::class,
    ConnectDI::class,
    SettingsDI::class
  ]
)
interface AMessageDI {

  @Module
  class AmessageModule

  companion object {

    private var component: AMessageDI? = null

    @Synchronized
    fun init(context: Context): AMessageDI = component ?: DaggerAMessageDI.builder()
      .connectionsDI(ConnectionsDI.init(context))
      .conversationsDI(ConversationsDI.init(context))
      .connectDI(ConnectDI.init(context))
      .settingsDI(SettingsDI.init(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): AMessageDI = component ?: run { throw Exception("Not Initialized") }

  }

}
