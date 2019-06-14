package com.moonlitdoor.amessage.experiments

import org.koin.core.KoinComponent
import org.koin.core.get

object Experiments : KoinComponent {

  enum class ABC {
    A, B, C
  }

  val TEST3 = Experiment("exp_test_3", ABC::class.java, ABC.A)
  val TEST2 = Experiment("exp_test_2")

  val experiments: List<Experiment<*>> = listOf(
    TEST3,
    TEST2
  )

  init {
    get<FirebaseRemoteConfigWrapper>().setDefaults(experiments.associateBy({ it.key }, { it.defaultValue }))
  }

}
