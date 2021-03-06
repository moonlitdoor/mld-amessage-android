package com.moonlitdoor.amessage.connections

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ConnectionsViewModel @Inject constructor(repository: ConnectionRepository) : ViewModel() {

  val screenState: Flow<ConnectionsScreenState> = repository.getConnected().map {
    when (it.isNotEmpty()) {
      true -> ConnectionsScreenState.Result(it)
      false -> ConnectionsScreenState.Empty
    }
  }
}
