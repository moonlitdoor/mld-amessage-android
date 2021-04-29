package com.moonlitdoor.amessage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.KeysProjection
import com.moonlitdoor.amessage.database.projection.TokenProjection
import java.time.Instant

@Entity(tableName = "connection", indices = [Index(value = ["token"], unique = true)])
data class ConnectionEntity(
  @PrimaryKey
  @ColumnInfo(name = "connection_id")
  val connectionId: IdProjection,
  @ColumnInfo(name = "profile_id")
  val profileId: IdProjection,
  val handle: HandleProjection,
  val token: TokenProjection,
  val keys: KeysProjection,
  @ColumnInfo(name = "associated_data")
  val associatedData: AssociatedDataProjection,
  val state: State,
  val scanned: Instant,
  val confirmed: Instant? = null,
  val deleted: Boolean = false
) {

  fun new() = ConnectionEntity(
    connectionId = IdProjection(connectionId.value),
    profileId = IdProjection(profileId.value),
    handle = HandleProjection(handle.value),
    token = TokenProjection(token.value),
    keys = KeysProjection(),
    associatedData = AssociatedDataProjection(),
    state = state,
    scanned = scanned,
  )

  sealed class State(val value: String) {
    object Scanned : State("scanned")
    object Queued : State("queued")
    object Pending : State("pending")
    object Invited : State("invited")
    object Connected : State("connected")
  }
}
