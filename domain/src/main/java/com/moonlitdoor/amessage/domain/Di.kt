@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage.domain

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.moonlitdoor.amessage.domain.dao.*
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.*
import com.moonlitdoor.amessage.domain.service.FirebaseMessagingServiceAdapter
import com.moonlitdoor.amessage.network.client.FirebaseClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val domainDi = module {

  single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
  single { get<Context>().resources }
  single { get<Retrofit>().create(FirebaseClient::class.java) }
  single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).addMigrations(*Migrations.ALL).build() }
  single { get<AppDatabase>().connectionDao() }
  single { get<AppDatabase>().conversationDao() }
  single { ProfileDao(get<SharedPreferences>()) }
  single { WindowsDao(androidContext(), get<SharedPreferences>()) }
  single { ConnectionRepository(get<ConnectionDao>(), get<ProfileDao>(), get<FirebaseClient>()) }
  single { ConversationRepository(get<ConversationDao>()) }
  single { ThemeRepository(get()) }
  single { ProfileRepository(get<ProfileDao>()) }
  single { WindowsRepository(get<WindowsDao>()) }
  single { ConversationFactory() }
  single { FirebaseMessagingServiceAdapter(get<ConnectionRepository>(), get<ProfileRepository>()) }

}
