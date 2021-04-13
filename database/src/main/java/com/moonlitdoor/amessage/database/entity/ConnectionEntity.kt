package com.moonlitdoor.amessage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.KeysProjection
import com.moonlitdoor.amessage.database.projection.PasswordProjection
import com.moonlitdoor.amessage.database.projection.SaltProjection
import com.moonlitdoor.amessage.database.projection.TokenProjection

@Entity(tableName = "connection", indices = [Index(value = ["connection_id"], unique = true), Index(value = ["token"], unique = true)])
data class ConnectionEntity(
  @ColumnInfo(name = "connection_id")
  val connectionId: IdProjection,
  val password: PasswordProjection,
  val salt: SaltProjection,
  val token: TokenProjection,
  val handle: HandleProjection,
  val associatedData: AssociatedDataProjection,
  val keys: KeysProjection,
  val state: State,
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0
) {

  fun new() = ConnectionEntity(
    connectionId = IdProjection(),
    password = PasswordProjection(),
    salt = SaltProjection(),
    token = TokenProjection(token.value),
    handle = HandleProjection(handle.value),
    associatedData = AssociatedDataProjection(),
    keys = KeysProjection(),
    state = state
  )

  sealed class State(val value: String) {
    object Scanned : State("scanned")
    object Queued : State("queued")
    object Pending : State("pending")
    object Invited : State("invited")
    object Connected : State("connected")
  }

}
