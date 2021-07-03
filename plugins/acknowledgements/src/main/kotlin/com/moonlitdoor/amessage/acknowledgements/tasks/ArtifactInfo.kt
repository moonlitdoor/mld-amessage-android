package com.moonlitdoor.amessage.acknowledgements.tasks

data class ArtifactInfo(
  val name: String,
  val description: String?,
  val group: String,
  val artifact: String,
  val version: String,
  val license: String,
  val url: String
) {
  override fun toString(): String = "$name|$description|$group|$artifact|$version|$license|$url"
}
