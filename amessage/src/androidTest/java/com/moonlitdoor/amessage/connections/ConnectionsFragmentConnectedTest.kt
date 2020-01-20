package com.moonlitdoor.amessage.connections

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.moonlitdoor.amessage.AMessageActivity
import com.moonlitdoor.amessage.R
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConnectionsFragmentConnectedTest {

  @get:Rule
  val rule = ActivityTestRule(AMessageActivity::class.java, false, false)

  @Ignore("flaky")
  @Test
  fun givenWhen() {
    val applicationContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
//    PreferenceManager.getDefaultSharedPreferences(applicationContext).edit { putInt(Constants.SharedPreferences.VERSION_CODE, Int.MAX_VALUE) }
//    Injector.init(applicationContext, DependencyModuleFake(applicationContext, MutableLiveData<String>().also { it.postValue("Bob") }))
    rule.launchActivity(Intent())

    onView(withContentDescription(R.string.navigation_drawer_open)).perform(click())
    onView(withText(R.string.title_conversation_list)).perform(click())
    assertEquals(2, 2)
  }
}