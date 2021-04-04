package com.moonlitdoor.amessage.connection

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(private val repository: ConnectionRepository) : ViewModel() {

  fun getConnection(connectionId: Long): Flow<Connection> = repository.getConnection(connectionId = connectionId)

}
