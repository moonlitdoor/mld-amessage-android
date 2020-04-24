plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.RESOURCES))
  implementation(project(M.EXTENSIONS))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Com.Google.Firebase.firebaseConfig)
  implementation(D.Com.Google.Firebase.firebaseIid)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Com.Google.Truth.truth)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(D.Com.Google.Truth.truth)

}
