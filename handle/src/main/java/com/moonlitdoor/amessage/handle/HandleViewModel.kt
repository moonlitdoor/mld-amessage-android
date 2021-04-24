package com.moonlitdoor.amessage.handle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HandleViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

  private val _handle = MutableStateFlow(Handle(""))
  var handle = _handle.asStateFlow()
  val isHandleSet: Flow<Boolean> = repository.handleIsSet()

  fun setHandle(handle: String) {
    _handle.compareAndSet(_handle.value, Handle(handle))
  }

  fun saveHandle() = viewModelScope.launch(Dispatchers.IO) {
    Timber.d("Setting Handle to: ${_handle.value}")
    repository.setHandle(handle = _handle.value)
  }
}
