package com.moonlitdoor.amessage.network.json

class FirebaseResponseJson {

  private val multicast_id: Long = 0
  private val success: Int = 0
  private val failure: Int = 0
  private val canonical_ids: Int = 0
  private val results: List<ResultJson>? = null

  private class ResultJson {

    private val message_id: String? = null
  }
}
