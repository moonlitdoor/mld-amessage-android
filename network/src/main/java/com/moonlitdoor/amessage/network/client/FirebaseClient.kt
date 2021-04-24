package com.moonlitdoor.amessage.network.client

import com.moonlitdoor.amessage.dto.FirebaseMessageDto
import com.moonlitdoor.amessage.dto.FirebaseResponseJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseClient {

  @Headers("Content-Type:application/json")
  @POST("/api/message/send")
  suspend fun send(@Body message: FirebaseMessageDto): Response<FirebaseResponseJson>
}
