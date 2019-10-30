package com.moonlitdoor.amessage.experiments

import org.koin.core.KoinComponent
import org.koin.core.get

object Experiments : KoinComponent {

  enum class ABC {
    A, B, C
  }

  val TEST3 = Experiment("exp_test_3", ABC::class.java, ABC.A)
  val TEST2 = Experiment("exp_test_2")
  val FEATURE_WINDOWS = Experiment("exp_feature_windows")
  val FEATURE_SETTINGS = Experiment("exp_feature_settings", Experiment.BOOLEAN.TRUE)
  val FEATURE_HELP = Experiment("exp_feature_help")
  val FEATURE_FEEDBACK = Experiment("exp_feature_feedback")
  val FEATURE_ABOUT = Experiment("exp_feature_about")
  val FEATURE_WHATS_NEW = Experiment("exp_feature_whats_new")
  val USE_COMPOSE = Experiment("exp_use_compose")

  val experiments: List<Experiment<*>> = listOf(
    FEATURE_WINDOWS,
    FEATURE_SETTINGS,
    FEATURE_HELP,
    FEATURE_FEEDBACK,
    FEATURE_ABOUT,
    FEATURE_WHATS_NEW,
    TEST3,
    TEST2,
    USE_COMPOSE
  )

  fun init(): Unit = get<FirebaseRemoteConfigWrapper>().setDefaults(experiments.associateBy({ it.key }, { it.defaultValue }))


}
