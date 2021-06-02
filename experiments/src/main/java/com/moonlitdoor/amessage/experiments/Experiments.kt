package com.moonlitdoor.amessage.experiments

object Experiments {

  val FEATURE_ABOUT = Experiment(key = "exp_feature_about")
  val FEATURE_FAQ = Experiment(key = "exp_feature_faq")
  val FEATURE_WHATS_NEW = Experiment(key = "exp_feature_whats_new")
  val FEATURE_FEEDBACK = Experiment(key = "exp_feature_feedback")
  val FEATURE_HELP = Experiment(key = "exp_feature_help")
  val FEATURE_SETTINGS = Experiment(key = "exp_feature_settings")
  val FEATURE_WINDOWS = Experiment(key = "exp_feature_windows")

  val experiments: List<Experiment<*>> = listOf(
    FEATURE_ABOUT,
    FEATURE_FAQ,
    FEATURE_WHATS_NEW,
    FEATURE_FEEDBACK,
    FEATURE_HELP,
    FEATURE_SETTINGS,
    FEATURE_WINDOWS,
  )

  fun init(): Unit = FirebaseRemoteConfigWrapper.get().setDefaults(experiments.associateBy({ it.key }, { it.defaultValue }))
}
