package com.moonlitdoor.amessage.domain.repository

//import androidx.annotation.MainThread
//import androidx.lifecycle.LiveData/**/
//import androidx.work.WorkManager
import androidx.work.WorkManager
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
//import com.moonlitdoor.amessage.database.dao.ProfileDao
//import com.moonlitdoor.amessage.database.entity.ConnectionEntity
//import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker
import kotlinx.coroutines.flow.Flow
//import com.moonlitdoor.amessage.domain.work.ConnectionConfirmationWorker
//import com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker
//import com.moonlitdoor.amessage.extensions.map
//import com.moonlitdoor.amessage.network.NetworkClient
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class ConnectionRepository @Inject constructor(private val connectionDao: ConnectionDao,/* private val profileDao: ProfileDao,*/ private val workManager: WorkManager/* private val client: NetworkClient*/) {

  fun getConnected() = connectionDao.getConnected().map { list -> list.map { Connection.from(it) } }

  fun getPending() = connectionDao.getPending().map { list -> list.map { Connection.from(it) } }

  fun getInvited() = connectionDao.getInvited().map { list -> list.map { Connection.from(it) } }

//  suspend fun getConnectedConnections2(): List<Connection> = connectionDao.getConnected2().map {
//    Connection.from(it)
//  }

  fun getInvitedConnections() = Unit//: Flow<List<Connection>> = connectionDao.getInvitedConnections().map { list -> list.map { Connection.from(it) } }

//  @MainThread
//  fun getScannedInvitedAndPendingConnections(): LiveData<List<Connection>> = connectionDao.getScannedInvitedAndPendingConnections().map { it.map { Connection.from(it) } }

  suspend fun create(scannedProfile: Profile) {
    ConnectionMapper.fromScanned(scannedProfile).also { entity ->
      if (connectionDao.insert(entity) > 0) {
        workManager.enqueue(ConnectionInviteWorker.request().setInputData(ConnectionInviteWorker.data(entity.connectionId, scannedProfile)).build())
      }
    }
  }

  suspend fun confirm(connection: Connection) {
    connectionDao.insert(ConnectionMapper.fromConnected(connection))
//      workManager.enqueue(ConnectionConfirmationWorker.request().setInputData(ConnectionConfirmationWorker.data(connection.connectionId)).build())
//    }
  }

  fun reject(connection: Connection) {

//    connectionDao.delete(ConnectionMapper.from(connection))
//    val response = client.send(FirebaseMessageJson(ConnectionRejectionPayload(), ConnectionMapper.toJson(connection))).execute()
//    Timber.d(response.isSuccessful.toString())
//    Timber.d(response.message())

  }

  suspend fun connectionCount() = connectionDao.connectionCount()

  fun update(connectionId: UUID, state: Connection.State) = connectionDao.update(connectionId, ConnectionMapper.state(state))

  //  TODO remove ConnectionEntity
  suspend fun insert(entity: ConnectionEntity) = connectionDao.insert(entity)
  suspend fun insert(entity: Connection) = connectionDao.insert(ConnectionMapper.from(entity))

  fun delete(connectionId: UUID) = Unit//connectionDao.delete(connectionId)

  fun getConnection(connectionId: Long): Flow<Connection> = connectionDao.getConnection(connectionId).map { ConnectionMapper.map(it) }

  suspend fun isConnectionExisting(connection: Connection): Boolean = connectionDao.isConnectionExisting(ConnectionMapper.from(connection).connectionId) > 0


}