package com.moonlitdoor.amessage.network.client

import com.moonlitdoor.amessage.network.json.FirebaseMessageJson
import com.moonlitdoor.amessage.network.json.FirebaseResponseJson
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseClient {

  @Headers("Content-Type:application/json", "Authorization:key=AAAAGLbgIi8:APA91bEmzVNYQcnloyNHr9aggrpeGzAj4H27mYQ3Geqr7w3x9mRRWvbQVwu0iGTT20su9Os9mA1gyKOG1aTte0bxfLcOB_dmuhu7a1Bz851FU-zRTfWhehHae_VkzKLQbbLM2cD8Vhqw")
  @POST("/fcm/send")
  fun send(@Body message: FirebaseMessageJson): Call<FirebaseResponseJson>

}
