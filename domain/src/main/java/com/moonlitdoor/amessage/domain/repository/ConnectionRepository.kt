package com.moonlitdoor.amessage.domain.repository

import androidx.work.WorkManager
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.mapper.ConnectionStateMapper
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.work.ConnectionConfirmationWorker
import com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class ConnectionRepository @Inject constructor(
  private val connectionDao: ConnectionDao,
  /* private val profileDao: ProfileDao,*/
  private val workManager: WorkManager,
) {
  suspend fun get(connectionId: UUID): Connection = ConnectionMapper.map(connectionDao.get(connectionId))

  fun getFlow(connectionId: UUID): Flow<Connection> = connectionDao.getFlow(connectionId).map { ConnectionMapper.map(it) }

  fun getConnected() = connectionDao.getConnected().map { list -> list.map { ConnectionMapper.map(it) } }

  fun getPending() = connectionDao.getPending().map { list -> list.map { ConnectionMapper.map(it) } }

  fun getInvited() = connectionDao.getInvited().map { list -> list.map { ConnectionMapper.map(it) } }

  suspend fun create(connection: Connection) {
    ConnectionMapper.mapToEntity(connection).also { scannedConnection ->
      scannedConnection.new().let { newConnection ->
        if (connectionDao.insert(newConnection) > 0) {
          workManager.enqueue(ConnectionInviteWorker.request().setInputData(ConnectionInviteWorker.data(newConnection = newConnection, scannedConnection = scannedConnection)).build())
        }
      }
    }
  }

  suspend fun confirm(connection: Connection) {
    if (connectionDao.update(ConnectionMapper.mapToEntity(connection)) != 0) {
      workManager.enqueue(ConnectionConfirmationWorker.request().setInputData(ConnectionConfirmationWorker.data(connection.connectionId)).build())
    }
  }

  suspend fun reject(connection: Connection) {
    connectionDao.delete(ConnectionMapper.mapToEntity(connection))
//    val response = client.send(FirebaseMessageJson(ConnectionRejectionPayload(), ConnectionMapper.toJson(connection))).execute()
//    Timber.d(response.isSuccessful.toString())
//    Timber.d(response.message())
  }

  suspend fun connectionCount() = connectionDao.connectionCount()

  suspend fun update(connectionId: UUID, state: Connection.State) = connectionDao.update(connectionId, ConnectionStateMapper.map(state))

  suspend fun update(connection: Connection): Int = connectionDao.update(ConnectionMapper.mapToEntity(connection))

  //  TODO remove ConnectionEntity
  suspend fun insert(entity: ConnectionEntity) = connectionDao.insert(entity)
  suspend fun insert(entity: Connection) = connectionDao.insert(ConnectionMapper.mapToEntity(entity))

  fun delete(connectionId: UUID) = Unit // connectionDao.delete(connectionId)

  suspend fun isConnectionExisting(connection: Connection): Boolean = connectionDao.isConnectionExisting(ConnectionMapper.mapToEntity(connection).connectionId.value) > 0
}
