@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage.domain

import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.dao.WindowsDao
import com.moonlitdoor.amessage.database.databaseDi
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.*
import com.moonlitdoor.amessage.domain.service.FirebaseMessagingServiceAdapter
import com.moonlitdoor.amessage.network.client.FirebaseClient
import com.moonlitdoor.amessage.network.networkDi
import org.koin.dsl.module

val domainDi = databaseDi + networkDi + listOf(module {

  single { ConnectionRepository(get<ConnectionDao>(), get<ProfileDao>(), get<FirebaseClient>()) }
  single { ConversationRepository(get<ConversationDao>()) }
  single { ThemeRepository(get()) }
  single { ProfileRepository(get<ProfileDao>()) }
  single { WindowsRepository(get<WindowsDao>()) }
  single { ConversationFactory() }
  single { FirebaseMessagingServiceAdapter(get<ConnectionRepository>(), get<ProfileRepository>()) }

})
