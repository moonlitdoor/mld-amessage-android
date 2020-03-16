package com.moonlitdoor.amessage.experiments

object Experiments {

  enum class ABC {
    A, B, C
  }

  val TEST1 = Experiment(key = "exp_test_1")
  val TEST2 = Experiment(key = "exp_test_2")
  val TEST3 = Experiment(key = "exp_test_3", c = ABC::class.java, defaultValue = ABC.A)
  val TEST4 = Experiment(key = "exp_test_4", title = "This is a title")
  val TEST5 = Experiment(key = "exp_test_5", description = "this is a description")
  val TEST6 = Experiment(key = "exp_test_6", title = "This is a title", description = "this is a description")
  val FEATURE_WINDOWS = Experiment(key = "exp_feature_windows")
  val FEATURE_SETTINGS = Experiment(key = "exp_feature_settings", defaultValue = Experiment.BOOLEAN.TRUE)
  val FEATURE_HELP = Experiment(key = "exp_feature_help")
  val FEATURE_FEEDBACK = Experiment(key = "exp_feature_feedback")
  val FEATURE_ABOUT = Experiment(key = "exp_feature_about")
  val FEATURE_WHATS_NEW = Experiment(key = "exp_feature_whats_new")
  val USE_COMPOSE_EXPERIMENTS = Experiment(key = "exp_use_compose_experiments")

  val experiments: List<Experiment<*>> = listOf(
    USE_COMPOSE_EXPERIMENTS,
    FEATURE_WINDOWS,
    FEATURE_SETTINGS,
    FEATURE_HELP,
    FEATURE_FEEDBACK,
    FEATURE_ABOUT,
    FEATURE_WHATS_NEW,
    TEST1,
    TEST2,
    TEST3,
    TEST4,
    TEST5,
    TEST6
  )

  fun init(): Unit = ExperimentsDI.get().firebaseRemoteConfigWrapper().setDefaults(experiments.associateBy({ it.key }, { it.defaultValue }))


}
