package com.moonlitdoor.amessage.domain.model

import java.util.*

data class Connection(
  val id: Long = 0,
  val connectionId: Id,
  val handle: Handle,
  val token: Token,
  val associatedData: AssociatedData,
  val keys: Keys,
  val state: State
) {

  constructor(parts: String) : this(
    handle = Handle(parts.split("|")[0]),
    token = Token(parts.split("|")[1]),
    connectionId = Id(UUID.fromString(parts.split("|")[2])),
    associatedData = AssociatedData(UUID.fromString(parts.split("|")[3])),
    keys = Keys(parts.split("|")[4]),
    state = State.Scanned
  )

  sealed class State(val value: String) {
    object Scanned : State("scanned")
    object Queued : State("queued")
    object Pending : State("pending")
    object Invited : State("invited")
    object Connected : State("connected")
  }
}
