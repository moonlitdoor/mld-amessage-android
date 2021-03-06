package com.moonlitdoor.amessage.domain.model

import java.time.Instant
import java.util.UUID

data class Connection(
  val profileId: Id,
  val connectionId: Id,
  val handle: Handle,
  val token: Token,
  val associatedData: AssociatedData,
  val keys: Keys,
  val state: State,
  val scanned: Instant,
  val confirmed: Instant? = null,
) {

  constructor(parts: String) : this(
    handle = Handle(parts.split("|")[0]),
    token = Token(parts.split("|")[1]),
    profileId = Id(UUID.fromString(parts.split("|")[2])),
    connectionId = Id(),
    associatedData = AssociatedData(UUID.fromString(parts.split("|")[3])),
    keys = Keys(parts.split("|")[4]),
    state = State.Scanned,
    scanned = Instant.now()
  )

  sealed class State(val value: String) {
    object Scanned : State("scanned")
    object Queued : State("queued")
    object Pending : State("pending")
    object Invited : State("invited")
    object Connected : State("connected")
  }
}
