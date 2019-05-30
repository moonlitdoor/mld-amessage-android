@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage

import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connection.ConnectionViewModel
import com.moonlitdoor.amessage.conversation.ConversationViewModel
import com.moonlitdoor.amessage.conversation.create.ConversationCreateViewModel
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.*
import com.moonlitdoor.amessage.handle.HandleViewModel
import com.moonlitdoor.amessage.navigation.NavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val di = module {

  viewModel { ConnectViewModel(get<ConnectionRepository>(), get<ProfileRepository>()) }
  viewModel { ConnectionViewModel(get<ConnectionRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { ConversationViewModel(get<ConversationRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { HandleViewModel(get<ProfileRepository>()) }
  viewModel { ConversationCreateViewModel(get<ConversationRepository>(), get<ConnectionRepository>(), get<ConversationFactory>()) }
  viewModel { NavigationViewModel(get<ThemeRepository>()) }

}
