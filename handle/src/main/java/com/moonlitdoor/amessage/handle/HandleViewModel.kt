package com.moonlitdoor.amessage.handle

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HandleViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {

  @MainThread
  fun setHandle(handle: String) = viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
  }) { withContext(Dispatchers.IO) { profileRepository.setHandle(handle) } }

  val profile: LiveData<Profile> = profileRepository.getProfile().asLiveData()

}