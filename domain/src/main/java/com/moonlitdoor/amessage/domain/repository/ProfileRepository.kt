package com.moonlitdoor.amessage.domain.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.extensions.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao) {

  val profile: LiveData<Profile?> = profileDao.profile.map {
    Profile.from(it)
  }

  //TODO notify connections of handle change
  fun setHandle(handle: String) = profileDao.setHandle(handle)


  //TODO notify connections of token change
  @WorkerThread
  fun setToken(token: String) = profileDao.setToken(token)

  val handle: LiveData<String?> = profileDao.handle

  fun getProfile(): Profile? = Profile.from(profileDao.getProfileSync())

}