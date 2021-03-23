package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.domain.mapper.HandleMapper
import com.moonlitdoor.amessage.domain.mapper.IdMapper
import com.moonlitdoor.amessage.domain.mapper.PasswordMapper
import com.moonlitdoor.amessage.domain.mapper.ProfileMapper
import com.moonlitdoor.amessage.domain.mapper.SaltMapper
import com.moonlitdoor.amessage.domain.mapper.TokenMapper
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.domain.model.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val dao: ProfileDao) {

  fun getProfile(): Flow<Profile> = dao.getProfile().map { ProfileMapper.map(it) }

  //TODO notify connections of handle change
  suspend fun setHandle(handle: Handle) = dao.insertHandle(HandleMapper.map(handle))

  //TODO notify connections of token change
  suspend fun setToken(token: Token) = dao.insertToken(TokenMapper.map(token))

  suspend fun getHandle() = HandleMapper.map(dao.getHandle())

  suspend fun getToken() = TokenMapper.map(dao.getToken())

  suspend fun getId() = IdMapper.map(dao.getId())

  suspend fun getPassword() = PasswordMapper.map(dao.getPassword())

  suspend fun getSalt() = SaltMapper.map(dao.getSalt())

}