package com.moonlitdoor.amessage.network.client

import com.moonlitdoor.amessage.network.json.FirebaseMessageJson
import com.moonlitdoor.amessage.network.json.FirebaseResponseJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseClient {

  @Headers("Content-Type:application/json")
  @POST("/api/message/send")
  suspend fun send(@Body message: FirebaseMessageJson): Response<FirebaseResponseJson>

}
