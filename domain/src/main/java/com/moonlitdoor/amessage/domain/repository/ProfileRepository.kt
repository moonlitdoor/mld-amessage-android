package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao) {

  fun getProfile(): Flow<Profile> = profileDao.getProfile().map { Profile.from(it) }

  fun getHandle(): Flow<HandleProjection> = profileDao.getHandle()

  //TODO notify connections of handle change
  suspend fun setHandle(handle: String) = profileDao.insertHandle(handle)

  //TODO notify connections of token change
  suspend fun setToken(token: String) = profileDao.insertToken(token)

}