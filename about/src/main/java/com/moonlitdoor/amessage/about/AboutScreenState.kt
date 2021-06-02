package com.moonlitdoor.amessage.about

sealed class AboutScreenState {
  object Loading : AboutScreenState()
  data class Data(
    val version: String,
    val buildDate: String,
    val acknowledgements: List<Acknowledgement>
  ) : AboutScreenState()
}
