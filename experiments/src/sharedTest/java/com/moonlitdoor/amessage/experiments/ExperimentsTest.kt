package com.moonlitdoor.amessage.experiments

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import dagger.Provides
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExperimentsTest {

  class TestExperimentsModule(context: Context) : ExperimentsDI.ExperimentsModule(context) {

    @Provides
    override fun providesFirebaseRemoteConfigWrapper(): FirebaseRemoteConfigWrapper = FirebaseRemoteConfigFake(
      defaultsHandler = {
        assertThat(it).hasSize(8)
        assertThat(it).containsEntry("exp_test_3", Experiments.ABC.A)
        assertThat(it).containsEntry("exp_test_2", Experiment.BOOLEAN.FALSE)
      },
      stringHandler = {
        when (it) {
          "exp_test_2" -> "TRUE"
          "exp_test_3" -> "A"
          else -> ""
        }
      })
  }

  @Before
  fun setup() {
    ExperimentsDI.init(InstrumentationRegistry.getInstrumentation().targetContext, TestExperimentsModule(InstrumentationRegistry.getInstrumentation().targetContext))
  }


  @After
  fun teardown() {
    ExperimentsDI.unload()
  }

  @Test
  fun givenExperiments_whenInitialized_thenAMapOfDefaultValuesIsSentToFirebaseRemoteConfig() {
    Experiments.experiments
  }

  @Test
  fun givenExperimentsTEST2_whenTheKeyIsChecked_thenTheValueIsExp_Test2() = assertEquals("exp_test_2", Experiments.TEST2.key)

  @Test
  fun givenExperimentsTEST2_whenTheIdIsChecked_thenTheValueContainsTheKey() = assertThat(Experiments.TEST2.id).contains("exp_test_2")

  @Test
  fun givenExperimentsTEST2_whenTheTitleIsChecked_thenTheValueIsNull() = assertEquals(null, Experiments.TEST2.title)

  @Test
  fun givenExperimentsTEST2_whenTheDescriptionIsChecked_thenTheValueIsNull() = assertEquals(null, Experiments.TEST2.description)

  @Test
  fun givenExperimentsTEST2_whenTheDefaultValueIsChecked_thenTheValueIsBoolean_FALSE() = assertEquals(Experiment.BOOLEAN.FALSE, Experiments.TEST2.defaultValue)

  @Test
  fun givenExperimentsTEST2_whenTheLocalValueIsChecked_thenTheValueIsNull() = assertEquals(Experiment.REMOTE, Experiments.TEST2.localValue)

  @Test
  fun givenExperimentsTEST2_whenTheRemoteValueIsChecked_thenTheValueIsNull() = assertEquals("TRUE", Experiments.TEST2.remoteValue)

  @Test
  fun givenExperimentsTEST2_whenTheValueIsChecked_thenTheValueFALSE() = assertEquals(Experiment.BOOLEAN.TRUE, Experiments.TEST2.value)

  @Test
  fun givenExperimentsTEST3_whenTheKeyIsChecked_thenTheValueIsExp_Test2() = assertEquals("exp_test_3", Experiments.TEST3.key)

  @Test
  fun givenExperimentsTEST3_whenTheIdIsChecked_thenTheValueContainsTheKey() = assertThat(Experiments.TEST3.id).contains("exp_test_3")

  @Test
  fun givenExperimentsTEST3_whenTheTitleIsChecked_thenTheValueIsNull() = assertEquals(null, Experiments.TEST3.title)

  @Test
  fun givenExperimentsTEST3_whenTheDescriptionIsChecked_thenTheValueIsNull() = assertEquals(null, Experiments.TEST3.description)

  @Test
  fun givenExperimentsTEST3_whenTheDefaultValueIsChecked_thenTheValueIsABC_FALSE() = assertEquals(Experiments.ABC.A, Experiments.TEST3.defaultValue)

  @Test
  fun givenExperimentsTEST3_whenTheLocalValueIsChecked_thenTheValueIsNull() = assertEquals(Experiment.REMOTE, Experiments.TEST3.localValue)

  @Test
  fun givenExperimentsTEST3_whenTheRemoteValueIsChecked_thenTheValueIsNull() = assertEquals("A", Experiments.TEST3.remoteValue)

  @Test
  fun givenExperimentsTEST3_whenTheValueIsChecked_thenTheValueFALSE() = assertEquals(Experiments.ABC.A, Experiments.TEST3.value)

}
