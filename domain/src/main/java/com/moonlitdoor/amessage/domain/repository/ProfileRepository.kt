package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.domain.mapper.AssociatedDataMapper
import com.moonlitdoor.amessage.domain.mapper.HandleMapper
import com.moonlitdoor.amessage.domain.mapper.IdMapper
import com.moonlitdoor.amessage.domain.mapper.KeysMapper
import com.moonlitdoor.amessage.domain.mapper.ProfileMapper
import com.moonlitdoor.amessage.domain.mapper.TokenMapper
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.domain.model.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val dao: ProfileDao) {

  fun getProfile(): Flow<Profile> = dao.getProfile().map { ProfileMapper.map(it) }

  fun handleIsSet(): Flow<Boolean> = dao.getProfile().map { it.handle.value.isNotEmpty() }

  // TODO notify connections of handle change
  suspend fun setHandle(handle: Handle): Unit = dao.setHandle(HandleMapper.map(handle))

  // TODO notify connections of token change
  suspend fun setToken(token: Token): Unit = dao.setToken(TokenMapper.map(token))

  suspend fun getId(): Id = IdMapper.map(dao.getId())

  suspend fun getKeys(): Keys = KeysMapper.map(dao.getKeys())

  suspend fun getAssociatedData(): AssociatedData = AssociatedDataMapper.map(dao.getAssociatedData())
}
