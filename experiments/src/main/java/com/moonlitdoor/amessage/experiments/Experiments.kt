package com.moonlitdoor.amessage.experiments

object Experiments {

  val FEATURE_FAQ = Experiment(key = "exp_feature_faq")
  val FEATURE_WHATS_NEW = Experiment(key = "exp_feature_whats_new")
  val FEATURE_ABOUT = Experiment(key = "exp_feature_about")
  val FEATURE_FEEDBACK = Experiment(key = "exp_feature_feedback")
  val FEATURE_HELP = Experiment(key = "exp_feature_help")
  val FEATURE_SETTINGS = Experiment(key = "exp_feature_settings", defaultValue = Experiment.BOOLEAN.TRUE)
  val FEATURE_WINDOWS = Experiment(key = "exp_feature_windows")

  val experiments: List<Experiment<*>> = listOf(
    FEATURE_WINDOWS,
    FEATURE_SETTINGS,
    FEATURE_HELP,
    FEATURE_FEEDBACK,
    FEATURE_ABOUT,
    FEATURE_FAQ,
    FEATURE_WHATS_NEW,
  )

  fun init(): Unit = FirebaseRemoteConfigWrapper.get().setDefaults(experiments.associateBy({ it.key }, { it.defaultValue }))

}
