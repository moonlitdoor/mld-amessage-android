package com.moonlitdoor.amessage.experiments

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declare

@RunWith(AndroidJUnit4::class)
class ExperimentTest : KoinTest {

  @Before
  fun setup() {
    startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
      modules(experimentsDi + testExperimentsDi)
    }
    declare {
      single {
        FirebaseRemoteConfigFake(
          stringHandler = {
            when (it) {
              "key" -> "FALSE"
              else -> ""
            }
          }) as FirebaseRemoteConfigWrapper
      }
    }
  }

  @After
  fun teardown() {
    stopKoin()
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
