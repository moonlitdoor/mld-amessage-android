package com.moonlitdoor.amessage.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.network.json.ConnectionInvitePayload
import java.util.*

@Entity(tableName = "connection", indices = [Index(value = ["connection_id"], unique = true)])
data class ConnectionEntity(
  @ColumnInfo(name = "connection_id")
  val connectionId: UUID,
  val password: UUID,
  val salt: UUID,
  val token: String,
  val handle: String,
  val state: Connection.State,
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0
) {
  companion object {
    fun from(connection: Connection) = ConnectionEntity(
      connection.connectionId,
      connection.password,
      connection.salt,
      connection.token,
      connection.handle,
      connection.state,
      connection.id
    )

    fun fromConnected(connection: Connection) = ConnectionEntity(
      connection.connectionId,
      connection.password,
      connection.salt,
      connection.token,
      connection.handle,
      Connection.State.Connected,
      connection.id
    )

    fun fromPending(profile: Profile) = ConnectionEntity(
      UUID.randomUUID(),
      UUID.randomUUID(),
      UUID.randomUUID(),
      profile.token,
      profile.handle,
      Connection.State.Pending
    )

    fun fromInvited(payload: ConnectionInvitePayload) = ConnectionEntity(
      payload.connectionId,
      payload.password,
      payload.salt,
      payload.token,
      payload.handle,
      Connection.State.Invited
    )
  }

}
