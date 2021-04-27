package com.moonlitdoor.amessage.domain.repository

import androidx.work.WorkManager
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.work.ConnectionConfirmWorker
import com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker
import com.moonlitdoor.amessage.domain.work.ConnectionRejectWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class ConnectionRepository @Inject constructor(
  private val connectionDao: ConnectionDao,
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
      ConnectionConfirmWorker.enqueue(workManager, connection.connectionId)
    }
  }

  suspend fun reject(connection: Connection) {
    if (connectionDao.deleteLogical(ConnectionMapper.mapToEntity(connection).connectionId.value) != 0) {
      ConnectionRejectWorker.enqueue(workManager, connection.connectionId)
    }
  }

  suspend fun connectionCount() = connectionDao.connectionCount()

  suspend fun update(connection: Connection): Int = connectionDao.update(ConnectionMapper.mapToEntity(connection))

  //  TODO remove ConnectionEntity
  suspend fun insert(entity: ConnectionEntity) = connectionDao.insert(entity)
  suspend fun insert(entity: Connection) = connectionDao.insert(ConnectionMapper.mapToEntity(entity))

  suspend fun delete(connectionId: UUID) = connectionDao.delete(connectionId)

  suspend fun isConnectionExisting(connection: Connection): Boolean = connectionDao.isConnectionExisting(ConnectionMapper.mapToEntity(connection).connectionId.value) > 0
}
