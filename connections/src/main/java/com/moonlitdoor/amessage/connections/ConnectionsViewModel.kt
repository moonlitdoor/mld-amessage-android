package com.moonlitdoor.amessage.connections

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectionsViewModel @Inject constructor(val repository: ConnectionRepository) : ViewModel()
