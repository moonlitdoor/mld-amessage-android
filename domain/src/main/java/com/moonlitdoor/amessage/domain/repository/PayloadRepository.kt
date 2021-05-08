package com.moonlitdoor.amessage.domain.repository

import androidx.work.WorkManager
import com.moonlitdoor.amessage.database.dao.PayloadDao
import com.moonlitdoor.amessage.domain.mapper.PayloadMapper
import com.moonlitdoor.amessage.domain.model.Payload
import com.moonlitdoor.amessage.domain.work.RemoteMessageWorker
import com.moonlitdoor.amessage.dto.PayloadDto
import javax.inject.Inject

class PayloadRepository @Inject constructor(
  private val dao: PayloadDao,
  private val workManager: WorkManager
) {

  suspend fun insert(payload: Payload): Unit = dao.insert(PayloadMapper.entity(payload)).also {
    RemoteMessageWorker.enqueue(workManager)
  }

  suspend fun get(types: List<PayloadDto.Type>): List<Payload> = dao.get(types.map { it.value }).map { PayloadMapper.map(it) }

  suspend fun delete(payload: Payload) = dao.delete(PayloadMapper.entity(payload))
}
