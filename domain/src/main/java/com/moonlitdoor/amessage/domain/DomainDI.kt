package com.moonlitdoor.amessage.domain

import android.content.Context
import com.moonlitdoor.amessage.database.DatabaseDI
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.FrequentlyAskedQuestionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.SettingsRepository
import com.moonlitdoor.amessage.domain.repository.ThemeRepository
import com.moonlitdoor.amessage.domain.repository.WindowsRepository
import com.moonlitdoor.amessage.domain.service.FirebaseMessagingService
import com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker
import com.moonlitdoor.amessage.network.NetworkDI
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
  modules = [
    DomainDI.DomainModule::class
  ],
  dependencies = [
    DatabaseDI::class,
    NetworkDI::class
  ]
)
@DomainDI.DomainScope
interface DomainDI {

  fun connectionRepository(): ConnectionRepository
  fun conversationRepository(): ConversationRepository
  fun frequentlyAskedQuestionRepository(): FrequentlyAskedQuestionRepository
  fun profileRepository(): ProfileRepository
  fun settingsRepository(): SettingsRepository
  fun themeRepository(): ThemeRepository
  fun windowsRepository(): WindowsRepository

  fun conversationFactory(): ConversationFactory

  fun inject(service: FirebaseMessagingService)
  fun inject(worker: ConnectionInviteWorker)

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class DomainScope

  @Module
  class DomainModule

  @Component.Factory
  interface Factory {
    fun create(databaseDI: DatabaseDI, networkDI: NetworkDI): DomainDI
  }

  companion object {

    private var component: DomainDI? = null

    @Synchronized
    fun init(context: Context): DomainDI = component ?: DaggerDomainDI.factory().create(
      databaseDI = DatabaseDI.init(context),
      networkDI = NetworkDI.init()
    ).also {
      component = it
    }

    @Synchronized
    fun get(): DomainDI = component ?: run { throw Exception("Not Initialized") }

  }

}
