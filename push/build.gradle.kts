plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.EXTENSIONS))
  implementation(project(M.DOMAIN))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)
  implementation(D.Com.Google.Firebase.firebaseAnalytics)
  implementation(D.Com.Google.Firebase.firebaseIid)
  implementation(D.Com.Google.Firebase.firebaseMessaging)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)
  testImplementation(D.Androidx.Test.core)
  testImplementation(D.Androidx.Test.Espresso.espressoCore)
  testImplementation(D.Androidx.Test.Espresso.espressoIdlingResource)
  testImplementation(D.Androidx.Test.Espresso.Idling.idlingConcurrent)
  testImplementation(D.Androidx.Test.rules)
  testImplementation(D.Androidx.Test.runner)
  testImplementation(D.Com.Google.Truth.truth)
  testImplementation(D.Junit.junit)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.core)
  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}