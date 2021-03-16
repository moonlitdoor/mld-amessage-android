//package com.moonlitdoor.amessage.connections
//
//import androidx.annotation.MainThread
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.asLiveData
//import androidx.lifecycle.liveData
//import androidx.lifecycle.viewModelScope
//import com.moonlitdoor.amessage.domain.model.Connection
//import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
//import com.moonlitdoor.amessage.domain.repository.ProfileRepository
//import com.moonlitdoor.amessage.domain.repository.WindowsRepository
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import javax.inject.Inject
//
//class ConnectionsViewModel @Inject constructor(connectionRepository: ConnectionRepository, private val profileRepository: ProfileRepository, windowsRepository: WindowsRepository) :
//  ViewModel() {
//
//  val connectionsConnected: LiveData<List<Connection>> = connectionRepository.getConnected().asLiveData()
//
//  val con: LiveData<List<Connection>> = liveData(viewModelScope.coroutineContext + Dispatchers.IO) { connectionRepository.getConnectedConnections2() }
//
//  val handle = profileRepository.getHandle().asLiveData()
//
//  @MainThread
//  fun setHandle(handle: String) = viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
//    println("CoroutineExceptionHandler got $exception")
//  }) { withContext(Dispatchers.IO) { profileRepository.setHandle(handle) } }
//
//  val windowsCount = windowsRepository.windowsCount
//
//}