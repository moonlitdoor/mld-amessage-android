plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
}

android {
  buildFeatures {
    dataBinding = true
  }
}

dependencies {

  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Com.Google.Firebase.firebaseCore)
  implementation(D.Com.Google.Firebase.firebaseIid)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)
}
