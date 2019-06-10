package com.moonlitdoor.amessage.network.client

import android.net.SSLCertificateSocketFactory
import android.net.SSLSessionCache
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.moonlitdoor.amessage.network.json.FirebaseMessageJson
import com.moonlitdoor.amessage.network.json.Payload
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
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
    server.hostName
//    server.port
    server.useHttps(SSLCertificateSocketFactory.getDefault(500, SSLSessionCache(InstrumentationRegistry.getInstrumentation().context)), false)
    val url = server.url("/fcm/send")
  }

  @After
  fun teardown() {
    server.shutdown()
    stopKoin()
  }

  @Ignore("not built out")
  @Test
  fun testClient() {
    val client: FirebaseClient = get()
    val response = client.send(
      FirebaseMessageJson(
        object : Payload() {
          override val type: Type
            get() = Type.ConnectionRejection
        },
        UUID.randomUUID(), "token", UUID.randomUUID(), UUID.randomUUID()
      )
    ).execute()
    assertTrue(response.isSuccessful)
  }

}