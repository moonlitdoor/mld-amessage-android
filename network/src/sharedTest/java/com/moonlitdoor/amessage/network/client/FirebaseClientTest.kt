package com.moonlitdoor.amessage.network.client

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moonlitdoor.amessage.network.FirebaseClientContainer
import com.moonlitdoor.amessage.network.TestNetworkDI
import com.moonlitdoor.amessage.network.json.FirebaseMessageJson
import com.moonlitdoor.amessage.network.json.Payload
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class FirebaseClientTest {

  private lateinit var server: MockWebServer
  private lateinit var container: FirebaseClientContainer

  @Before
  fun setup() {
    server = MockWebServer()
    server.start()
    server.dispatcher = object : Dispatcher() {
      override fun dispatch(request: RecordedRequest): MockResponse = when (request.path) {
        "/api/message/send" -> MockResponse().setResponseCode(200).setBody(
          """
          {
            "message": "fdshdfht",
            "code": "success"
          }
        """.trimIndent()
        )
        else -> MockResponse().setResponseCode(404)
      }
    }
    container = TestNetworkDI.init("http://${server.hostName}:${server.port}").inject(FirebaseClientContainer())
  }

  @After
  fun teardown() {
    server.shutdown()
  }

  @Test
  fun testClient() {
    runBlocking {
      val response = container.client.send(
        FirebaseMessageJson(
          object : Payload() {
            override val type: Type
              get() = Type.ConnectionRejection
          },
          UUID.randomUUID(), "token", UUID.randomUUID(), UUID.randomUUID()
        )
      )
      assertTrue(response.isSuccessful)
    }
  }

}