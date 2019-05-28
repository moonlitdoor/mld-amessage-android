package com.moonlitdoor.amessage.domain.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.domain.client.FirebaseClient
import com.moonlitdoor.amessage.domain.dao.ConnectionDao
import com.moonlitdoor.amessage.domain.dao.ProfileDao
import com.moonlitdoor.amessage.domain.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.json.ConnectionConfirmationPayload
import com.moonlitdoor.amessage.domain.json.ConnectionRejectionPayload
import com.moonlitdoor.amessage.domain.json.FirebaseMessageJson
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.extensions.map
import timber.log.Timber
import java.util.*

class ConnectionRepository(private val connectionDao: ConnectionDao, private val profileDao: ProfileDao, private val client: FirebaseClient) {

  @MainThread
  fun getConnectedConnections(): LiveData<List<Connection>> = connectionDao.getConnected().map { it.map { Connection.from(it) } }

  @MainThread
  fun getInvitedAndPendingConnections(): LiveData<List<Connection>> = connectionDao.getInvitedAndPendingConnections().map { it.map { Connection.from(it) } }

  @MainThread
  fun invite(profile: Profile) {

    profileDao.getProfileSync()?.let {
      val entity = ConnectionEntity.fromPending(profile).also {
        connectionDao.insert(it)
      }
//        WorkManager.getInstance().enqueue(OneTimeWorkRequestBuilder<ConnectionInviteWorker>().setInputData(ConnectionInviteWorker.data(it, entity, profile)).build())
    }

  }

  fun confirm(connection: Connection) {

    connectionDao.update(ConnectionEntity.fromConnected(connection))
    val response = client.send(FirebaseMessageJson(ConnectionConfirmationPayload(), connection)).execute()
    Timber.d(response.isSuccessful.toString())
    Timber.d(response.message())

  }

  fun reject(connection: Connection) {

    connectionDao.delete(ConnectionEntity.from(connection))
    val response = client.send(FirebaseMessageJson(ConnectionRejectionPayload(), connection)).execute()
    Timber.d(response.isSuccessful.toString())
    Timber.d(response.message())

  }

  fun update(connectionId: UUID, state: Connection.State) = connectionDao.update(connectionId, state)

  fun insert(entity: ConnectionEntity) = connectionDao.insert(entity)

  fun delete(connectionId: UUID) = connectionDao.delete(connectionId)

}