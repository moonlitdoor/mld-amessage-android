package com.moonlitdoor.amessage.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.moonlitdoor.amessage.AMessageActivity
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class FirebaseMessageUtil {

//  val client: FirebaseClient by inject()

  @Before
  fun setup() {
    val connectionId = UUID.randomUUID()
//    val result = client.send(
//      FirebaseMessageJson(ConnectionInvitePayload(ProfileObject.handle, ProfileObject.token, connectionId, ProfileObject.password, ProfileObject.salt), connectionId, ProfileObject.token, ProfileObject.password, ProfileObject.salt)
//    ).execute()
//    assertEquals(true, result.isSuccessful)
  }

  @get:Rule
  val rule = ActivityTestRule(AMessageActivity::class.java, false, false)

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
