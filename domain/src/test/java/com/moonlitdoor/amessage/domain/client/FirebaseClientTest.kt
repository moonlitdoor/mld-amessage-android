package com.moonlitdoor.amessage.domain.client

import android.net.SSLCertificateSocketFactory
import android.net.SSLSessionCache
import com.moonlitdoor.amessage.domain.json.FirebaseMessageJson
import com.moonlitdoor.amessage.domain.json.Payload
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.get
import org.koin.test.KoinTest
import org.robolectric.RuntimeEnvironment
import java.util.*

//@RunWith(AndroidJUnit4::class)
class FirebaseClientTest : KoinTest {

  private lateinit var server: MockWebServer

  //  @Before
  fun setup() {
    server = MockWebServer()
    server.hostName
    server.port
    server.useHttps(SSLCertificateSocketFactory.getDefault(500, SSLSessionCache(RuntimeEnvironment.application)), false)
    val url = server.url("/fcm/send")
  }

  @After
  fun teardown() {
    server.shutdown()
    stopKoin()
  }

  //  @Test
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