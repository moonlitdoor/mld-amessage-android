package com.moonlitdoor.amessage.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.moonlitdoor.amessage.ProfileObject
import com.moonlitdoor.amessage.domain.client.FirebaseClient
import com.moonlitdoor.amessage.domain.json.ConnectionInvitePayload
import com.moonlitdoor.amessage.domain.json.FirebaseMessageJson
import com.moonlitdoor.amessage.navigation.NavigationActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

@RunWith(AndroidJUnit4::class)
class FirebaseMessageUtil : KoinComponent {

  val client: FirebaseClient by inject()

  @Before
  fun setup() {
    val connectionId = UUID.randomUUID()
    val result = client.send(
      FirebaseMessageJson(ConnectionInvitePayload(ProfileObject.handle, ProfileObject.token, connectionId, ProfileObject.password, ProfileObject.salt), connectionId, ProfileObject.token, ProfileObject.password, ProfileObject.salt)
    ).execute()
    assertEquals(true, result.isSuccessful)
  }

  @get:Rule
  val rule = ActivityTestRule(NavigationActivity::class.java, false, false)

  @Ignore("flaky")
  @Test
  fun useAppContext() {
    rule.launchActivity(null)
//      rule.activity.findNavController(R.id.fragment).navigate(R.id.connectFragment)
  }

//    try {
//      Thread.sleep(100000)
//    } catch (e: InterruptedException) {
//      e.printStackTrace()
//    }


}
