package com.moonlitdoor.amessage.handle

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import javax.inject.Inject

class HandleViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {

  @MainThread
  fun setHandle(handle: String) = profileRepository.setHandle(handle)

  val profile: LiveData<Profile?> = profileRepository.profile

}