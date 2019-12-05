package com.moonlitdoor.amessage.about

data class Acknowledgement(
  val title: String,
  val copyright: String,
  val license: String,
  val licenseType: String
) {
  companion object {
    fun create(dependency: String): Acknowledgement = dependency.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().run {
      Acknowledgement(
        title = this[0],
        copyright = this[1],
        license = this[2],
        licenseType = this[3]
      )
    }
  }
}
