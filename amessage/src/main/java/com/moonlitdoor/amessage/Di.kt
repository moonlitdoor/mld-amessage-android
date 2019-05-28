@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage

import android.content.SharedPreferences
import androidx.room.Room
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connection.ConnectionViewModel
import com.moonlitdoor.amessage.conversation.ConversationViewModel
import com.moonlitdoor.amessage.conversation.create.ConversationCreateViewModel
import com.moonlitdoor.amessage.domain.client.FirebaseClient
import com.moonlitdoor.amessage.domain.dao.*
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.*
import com.moonlitdoor.amessage.domain.service.FirebaseMessagingServiceAdapter
import com.moonlitdoor.amessage.handle.HandleViewModel
import com.moonlitdoor.amessage.navigation.NavigationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val di = module {

  //  single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
//  single { get<Context>().resources }
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
  viewModel { ConnectViewModel(get<ConnectionRepository>(), get<ProfileRepository>()) }
  viewModel { ConnectionViewModel(get<ConnectionRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { ConversationViewModel(get<ConversationRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { HandleViewModel(get<ProfileRepository>()) }
  viewModel { ConversationCreateViewModel(get<ConversationRepository>(), get<ConnectionRepository>(), get<ConversationFactory>()) }
  viewModel { NavigationViewModel(get()) }
}

//@Suppress("UNUSED")
//private val diLogger = object : Logger {
//  override fun debug(msg: String) = Timber.d(msg).ignore()
//  override fun info(msg: String) = Timber.d(msg).ignore()
//  override fun err(msg: String) = Timber.d(msg).ignore()
//}