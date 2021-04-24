package com.moonlitdoor.amessage.experiments

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.moonlitdoor.amessage.root.Root
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExperimentsTest {

  private val firebaseRemoteConfigWrapper: FirebaseRemoteConfigWrapper = FirebaseRemoteConfigFake(
    defaultsHandler = {
      assertThat(it).hasSize(8)
    },
    stringHandler = {
      when (it) {
        "exp_test_2" -> "TRUE"
        "exp_test_3" -> "A"
        else -> ""
      }
    }
  )

  @Before
  fun setup() {
    Root.init(InstrumentationRegistry.getInstrumentation().targetContext as Application)
    FirebaseRemoteConfigWrapper.get(firebaseRemoteConfigWrapper)
  }

  @Test
  fun givenExperiments_whenInitialized_thenAMapOfDefaultValuesIsSentToFirebaseRemoteConfig() {
    Experiments.experiments
  }
}
