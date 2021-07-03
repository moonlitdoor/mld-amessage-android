package com.moonlitdoor.amessage.about

data class Acknowledgement(
  val name: String,
  val description: String?,
  val group: String,
  val artifact: String,
  val version: String,
  val title: String,
  val url: String,
) {

  val coordinates: String
    get() = "$group:$artifact:$version"

  companion object {
    fun create(dependency: String): Acknowledgement = dependency.split("|").toTypedArray().run {
      Acknowledgement(
        name = this[0],
        description = this[1].let { if (it == "null") null else it },
        group = this[2],
        artifact = this[3],
        version = this[4],
        title = this[5],
        url = this[6]
      )
    }
  }
}
