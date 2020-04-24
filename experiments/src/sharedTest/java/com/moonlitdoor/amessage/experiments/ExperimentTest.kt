package com.moonlitdoor.amessage.experiments

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.moonlitdoor.amessage.root.Root
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExperimentTest {

  private val firebaseRemoteConfigWrapper: FirebaseRemoteConfigWrapper = FirebaseRemoteConfigFake(
    stringHandler = {
      when (it) {
        "key" -> "FALSE"
        else -> ""
      }
    })

  @Before
  fun setup() {
    Root.init(InstrumentationRegistry.getInstrumentation().targetContext as Application)
    FirebaseRemoteConfigWrapper.get(firebaseRemoteConfigWrapper)
  }

  @Test
  fun givenAnExperimentWithAKey_thenTheKeyDoesNotChange() = assertEquals("key", Experiment("key").key)

  @Test
  fun givenAnExperimentWithAKey_thenIdIsComMoonlitDoorAmessageExperimentKey() = assertEquals("com.moonlitdoor.amessage.experiment.key", Experiment("key").id)

  @Test
  fun givenAnExperimentWithAKey_thenTheTitleIsNull() = assertNull(Experiment("key").title)

  @Test
  fun givenAnExperimentWithAKeyAndAStringTitle_thenTheTitleIsTheString() = assertEquals("Title", Experiment("key", "Title").title)

  @Test
  fun givenAnExperimentWithAKey_thenTheDescriptionIsNull() = assertNull(Experiment("key").description)

  @Test
  fun givenAnExperimentWithAKeyAndAStringDescription_thenTheDescriptionIsTheString() = assertEquals("description", Experiment("key", description = "description").description)

  @Test
  fun givenAnExperimentWithAKey_thenTheLocalValueIsRemote() = assertEquals("REMOTE", Experiment("key").localValue)

  @Test
  fun givenAnExperimentWithAKey_thenTheRemoteValueIsFALSE() = assertEquals("FALSE", Experiment("key").remoteValue)

  @Test
  fun givenAnExperimentWithAKey_thenTheValueIsFALSE() = assertEquals(Experiment.BOOLEAN.FALSE, Experiment("key").value)

  @Test
  fun givenAnExperimentWithAKey_whenLocalValueIsSetToTRUE_thenTheValueIsTRUE() {
    val experiment = Experiment("key")
    assertEquals(Experiment.BOOLEAN.FALSE, experiment.value)
    experiment.localValue = "TRUE"
    assertEquals(Experiment.BOOLEAN.TRUE, experiment.value)
  }
}
