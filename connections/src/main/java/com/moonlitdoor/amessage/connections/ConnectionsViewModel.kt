package com.moonlitdoor.amessage.connections

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ConnectionsViewModel @Inject constructor(val repository: ConnectionRepository) : ViewModel() {

  val viewState: Flow<ConnectionsViewState> = repository.getConnected().map {
    when (it.isNotEmpty()) {
      true -> ConnectionsViewState.Result(it)
      false -> ConnectionsViewState.Empty
    }

  }

}
