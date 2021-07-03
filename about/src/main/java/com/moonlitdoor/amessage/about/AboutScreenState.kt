package com.moonlitdoor.amessage.about

sealed class AboutScreenState {
  object Loading : AboutScreenState()
  data class Loaded(
    val version: String,
    val buildDate: String,
    val acknowledgements: List<Acknowledgement>
  ) : AboutScreenState()
}
