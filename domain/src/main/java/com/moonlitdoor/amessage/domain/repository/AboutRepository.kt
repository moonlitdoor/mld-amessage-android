package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.KeyValueRemoteDao
import com.moonlitdoor.amessage.domain.mapper.KeyValueMapper
import com.moonlitdoor.amessage.domain.model.KeyValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AboutRepository @Inject constructor(private val dao: KeyValueRemoteDao) {

  fun getKeyValues(): Flow<List<KeyValue>> = dao.getAboutKeyValues().map {
    KeyValueMapper.map(it)
  }
}
