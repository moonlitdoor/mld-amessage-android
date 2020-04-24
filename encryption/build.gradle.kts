plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
}

dependencies {

  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}

