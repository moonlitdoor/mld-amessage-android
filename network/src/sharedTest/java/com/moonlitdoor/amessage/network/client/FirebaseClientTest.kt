package com.moonlitdoor.amessage.network.client

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.moonlitdoor.amessage.network.json.FirebaseMessageJson
import com.moonlitdoor.amessage.network.json.Payload
import com.moonlitdoor.amessage.network.networkDi
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
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import java.util.*


@RunWith(AndroidJUnit4::class)
class FirebaseClientTest : KoinTest {

  private lateinit var server: MockWebServer

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
    startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
      androidFileProperties()
      modules(networkDi)
      properties(mapOf("firebase_url" to "http://${server.hostName}:${server.port}"))
    }
  }

  @After
  fun teardown() {
    stopKoin()
    server.shutdown()
  }

  @Test
  fun testClient() {
    runBlocking {
      val client: FirebaseClient = get()
      val response = client.send(
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