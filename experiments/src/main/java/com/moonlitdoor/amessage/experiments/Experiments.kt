package com.moonlitdoor.amessage.experiments

object Experiments {

  val FEATURE_ABOUT = Experiment(key = "exp_feature_about")
  val FEATURE_FAQ = Experiment(key = "exp_feature_faq")
  val FEATURE_WHATS_NEW = Experiment(key = "exp_feature_whats_new")
  val FEATURE_FEEDBACK = Experiment(key = "exp_feature_feedback")
  val FEATURE_HELP = Experiment(key = "exp_feature_help")
  val FEATURE_SETTINGS = Experiment(key = "exp_feature_settings")
  val FEATURE_WINDOWS = Experiment(key = "exp_feature_windows")
  val FEATURE_ABOUT_PRIVACY_POLICY = Experiment(key = "exp_feature_about_privacy_policy")
  val FEATURE_ABOUT_TERMS_OF_USE = Experiment(key = "exp_feature_about_terms_of_use")
  val FEATURE_ABOUT_WEBSITE = Experiment(key = "exp_feature_about_website")

  val experiments: List<Experiment<*>> = listOf(
    FEATURE_ABOUT,
    FEATURE_FAQ,
    FEATURE_WHATS_NEW,
    FEATURE_FEEDBACK,
    FEATURE_HELP,
    FEATURE_SETTINGS,
    FEATURE_WINDOWS,
    FEATURE_ABOUT_PRIVACY_POLICY,
    FEATURE_ABOUT_TERMS_OF_USE,
    FEATURE_ABOUT_WEBSITE
  )

  fun init(): Unit = FirebaseRemoteConfigWrapper.get().setDefaults(experiments.associateBy({ it.key }, { it.defaultValue }))
}
