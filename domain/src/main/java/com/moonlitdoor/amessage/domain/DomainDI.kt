package com.moonlitdoor.amessage.domain

import android.content.Context
import com.moonlitdoor.amessage.database.DatabaseDI
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.*
import com.moonlitdoor.amessage.domain.service.FirebaseMessagingService
import com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker
import com.moonlitdoor.amessage.network.NetworkDI
import dagger.Component
import dagger.Module

@Component(
  modules = [
    DomainDI.DomainModule::class
  ],
  dependencies = [
    DatabaseDI::class,
    NetworkDI::class
  ]
)
interface DomainDI {

  fun connectionRepository(): ConnectionRepository
  fun conversationRepository(): ConversationRepository
  fun profileRepository(): ProfileRepository
  fun themeRepository(): ThemeRepository
  fun windowsRepository(): WindowsRepository

  fun conversationFactory(): ConversationFactory

  fun inject(service: FirebaseMessagingService)
  fun inject(worker: ConnectionInviteWorker)

  @Module
  class DomainModule

  companion object {

    private var component: DomainDI? = null

    @Synchronized
    fun init(context: Context): DomainDI = component ?: DaggerDomainDI.builder()
      .databaseDI(DatabaseDI.init(context))
      .networkDI(NetworkDI.init())
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): DomainDI = component ?: run { throw Exception("Not Initialized") }

  }

}
