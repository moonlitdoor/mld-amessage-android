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
import com.moonlitdoor.amessage.network.client.FirebaseClient
import java.util.*

class ConnectionRepository(private val connectionDao: ConnectionDao, private val profileDao: ProfileDao, private val client: FirebaseClient) {

  @MainThread
  fun getConnectedConnections(): LiveData<List<Connection>> = connectionDao.getConnected().map { it.map { Connection.from(it) } }

  @MainThread
  fun getInvitedAndPendingConnections(): LiveData<List<Connection>> = connectionDao.getInvitedAndPendingConnections().map { it.map { Connection.from(it) } }

  @MainThread
  fun invite(profile: Profile) {

    profileDao.getProfileSync()?.let {
      val entity = ConnectionMapper.fromPending(profile).also {
        connectionDao.insert(it)
      }
//        WorkManager.getInstance().enqueue(OneTimeWorkRequestBuilder<ConnectionInviteWorker>().setInputData(ConnectionInviteWorker.data(it, entity, profile)).build())
    }

  }

  fun confirm(connection: Connection) {

    connectionDao.update(ConnectionMapper.fromConnected(connection))
//    val response = client.send(FirebaseMessageJson(ConnectionConfirmationPayload(), ConnectionMapper.toJson(connection))).execute()
//    Timber.d(response.isSuccessful.toString())
//    Timber.d(response.message())

  }

  fun reject(connection: Connection) {

    connectionDao.delete(ConnectionMapper.from(connection))
//    val response = client.send(FirebaseMessageJson(ConnectionRejectionPayload(), ConnectionMapper.toJson(connection))).execute()
//    Timber.d(response.isSuccessful.toString())
//    Timber.d(response.message())

  }

  fun update(connectionId: UUID, state: Connection.State) = connectionDao.update(connectionId, ConnectionMapper.state(state))

  fun insert(entity: ConnectionEntity) = connectionDao.insert(entity)

  fun delete(connectionId: UUID) = connectionDao.delete(connectionId)

}