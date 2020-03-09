package com.moonlitdoor.amessage.connections

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.WindowsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ConnectionViewModel @Inject constructor(connectionRepository: ConnectionRepository, private val profileRepository: ProfileRepository, windowsRepository: WindowsRepository) :
  ViewModel() {

  val connectedConnections = connectionRepository.getConnectedConnections()

  //   viewModelScope.coroutineContext + Dispatchers.IO
  val con: LiveData<List<Connection>> = liveData(viewModelScope.coroutineContext + Dispatchers.IO) { connectionRepository.getConnectedConnections2() }

  val handle = profileRepository.handle

  @MainThread
  fun setHandle(handle: String) = profileRepository.setHandle(handle)

  val windowsCount = windowsRepository.windowsCount

}