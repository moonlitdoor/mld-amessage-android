@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage

import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connection.ConnectionViewModel
import com.moonlitdoor.amessage.conversation.ConversationViewModel
import com.moonlitdoor.amessage.conversation.create.ConversationCreateViewModel
import com.moonlitdoor.amessage.domain.domainDi
import com.moonlitdoor.amessage.domain.factory.ConversationFactory
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ConversationRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.WindowsRepository
import com.moonlitdoor.amessage.settings.settingsDi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val di = domainDi + settingsDi + listOf(module {

  viewModel { ConnectViewModel(get<ConnectionRepository>(), get<ProfileRepository>()) }
  viewModel { ConnectionViewModel(get<ConnectionRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { ConversationViewModel(get<ConversationRepository>(), get<ProfileRepository>(), get<WindowsRepository>()) }
  viewModel { com.moonlitdoor.amessage.handle.HandleViewModel(get<ProfileRepository>()) }
  viewModel { ConversationCreateViewModel(get<ConversationRepository>(), get<ConnectionRepository>(), get<ConversationFactory>()) }

})
