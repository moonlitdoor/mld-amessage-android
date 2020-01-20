package com.moonlitdoor.amessage.domain.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.mapper.ConnectionMapper
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.extensions.map
import com.moonlitdoor.amessage.network.NetworkClient
import com.moonlitdoor.amessage.network.json.ConnectionConfirmationPayload
import com.moonlitdoor.amessage.network.json.ConnectionInvitePayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class ConnectionRepository @Inject constructor(private val connectionDao: ConnectionDao, private val profileDao: ProfileDao, private val client: NetworkClient) {

  @MainThread
  fun getConnectedConnections(): LiveData<List<Connection>> = connectionDao.getConnected().map { it.map { Connection.from(it) } }

  @MainThread
  fun getScannedInvitedAndPendingConnections(): LiveData<List<Connection>> = connectionDao.getScannedInvitedAndPendingConnections().map { it.map { Connection.from(it) } }

  suspend fun create(scannedProfile: Profile) {
    withContext(Dispatchers.IO) {
      profileDao.getProfileSync()?.let { myProfile ->
        Timber.i(scannedProfile.toString())
        Timber.i(myProfile.toString())
        ConnectionMapper.fromScanned(scannedProfile).also { entity ->
          Timber.i(entity.toString())
          if (connectionDao.insert(entity) > 0) {
            val payload = ConnectionInvitePayload(myProfile.handle, myProfile.token, entity.connectionId, entity.password, entity.salt)
            Timber.i(payload.toString())
            val status = client.send(payload, entity.connectionId, entity.token, scannedProfile.password, scannedProfile.salt)
            Timber.i(status.name)
          }

        }
//        WorkManager.getInstance().enqueue(OneTimeWorkRequestBuilder<ConnectionInviteWorker>().setInputData(ConnectionInviteWorker.data(it, entity, profile)).build())
      }
    }
  }

  suspend fun confirm(connection: Connection) {
    withContext(Dispatchers.IO) {
      connectionDao.update(ConnectionMapper.fromConnected(connection))
      /*val response =*/ client.send(ConnectionConfirmationPayload(), ConnectionMapper.toJson(connection))
//    Timber.d(response.isSuccessful.toString())
//    Timber.d(response.message())
    }
  }

  fun reject(connection: Connection) {

    connectionDao.delete(ConnectionMapper.from(connection))
//    val response = client.send(FirebaseMessageJson(ConnectionRejectionPayload(), ConnectionMapper.toJson(connection))).execute()
//    Timber.d(response.isSuccessful.toString())
//    Timber.d(response.message())

  }

  fun update(connectionId: UUID, state: Connection.State) = connectionDao.update(connectionId, ConnectionMapper.state(state))

  suspend fun insert(entity: ConnectionEntity) = connectionDao.insert(entity)

  fun delete(connectionId: UUID) = connectionDao.delete(connectionId)

}