plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {
  buildFeatures {
    dataBinding = true
  }
}

dependencies {

  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Androidx.Recyclerview.recyclerview)
  implementation(D.Androidx.AppCompat.appcompat)
  implementation(D.Com.JakeWharton.Timber.timber)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
