package com.moonlitdoor.amessage.domain.model

data class Profile(
  val handle: Handle,
  val token: Token,
  val id: Id,
  val associatedData: AssociatedData,
  val keys: Keys,
) {

  fun encode() = "${handle.value}|${token.value}|${id.value}|${associatedData.value}|${keys.value}"


}
