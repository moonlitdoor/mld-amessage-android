@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage

import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connection.ConnectionViewModel
import com.moonlitdoor.amessage.conversation.ConversationViewModel
import com.moonlitdoor.amessage.conversation.create.ConversationCreateViewModel
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.WindowsRepository
import com.moonlitdoor.amessage.handle.HandleViewModel
import com.moonlitdoor.amessage.navigation.NavigationViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val di = module {

  viewModel { ConnectViewModel(get<ConnectionRepository>(), get<ProfileRepository>()) }
  viewModel { ConnectionViewModel(get<ConnectionRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { ConversationViewModel(get<ConversationRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { HandleViewModel(get<ProfileRepository>()) }
  viewModel { ConversationCreateViewModel(get<ConversationRepository>(), get<ConnectionRepository>(), get<ConversationFactory>()) }
  viewModel { NavigationViewModel(get()) }
}
