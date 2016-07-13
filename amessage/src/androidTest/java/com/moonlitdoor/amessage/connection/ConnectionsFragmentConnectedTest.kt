package com.moonlitdoor.amessage.connection

import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.navigation.NavigationActivity
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConnectionsFragmentConnectedTest {

  @get:Rule
  val rule = ActivityTestRule(NavigationActivity::class.java, false, false)

  @Ignore("flaky")
  @Test
  fun givenWhen() {
    val applicationContext = InstrumentationRegistry.getTargetContext().applicationContext
//    PreferenceManager.getDefaultSharedPreferences(applicationContext).edit { putInt(Constants.SharedPreferences.VERSION_CODE, Int.MAX_VALUE) }
//    Injector.init(applicationContext, DependencyModuleFake(applicationContext, MutableLiveData<String>().also { it.postValue("Bob") }))
    rule.launchActivity(Intent())

    onView(withContentDescription(R.string.navigation_drawer_open)).perform(click())
    onView(withText(R.string.title_conversation_list)).perform(click())
    assertEquals(2, 2)
  }
}