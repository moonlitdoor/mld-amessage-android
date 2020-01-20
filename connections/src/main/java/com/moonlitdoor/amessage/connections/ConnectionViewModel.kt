package com.moonlitdoor.amessage.connections

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.WindowsRepository
import javax.inject.Inject

class ConnectionViewModel @Inject constructor(connectionRepository: ConnectionRepository, private val profileRepository: ProfileRepository, windowsRepository: WindowsRepository) : ViewModel() {

  val connectedConnections = connectionRepository.getConnectedConnections()

  val handle = profileRepository.handle

  @MainThread
  fun setHandle(handle: String) = profileRepository.setHandle(handle)

  val windowsCount = windowsRepository.windowsCount

}