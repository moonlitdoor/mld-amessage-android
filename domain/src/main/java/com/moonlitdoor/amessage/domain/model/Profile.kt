package com.moonlitdoor.amessage.domain.model

data class Profile(
  val handle: Handle,
  val token: Token,
  val id: Id,
  val password: Password,
  val salt: Salt,
  val associatedData: AssociatedData,
  val keys: Keys,
) {

  fun encode() = "${handle.value}|${token.value}|${id.value}|${password.value}|${salt.value}|${associatedData.value}|${keys.value}"


}
