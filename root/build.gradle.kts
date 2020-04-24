plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
}

dependencies {

  implementation(D.Androidx.AppCompat.appcompat)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

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
