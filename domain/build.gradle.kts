plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  api(project(M.DATABASE))
  api(project(M.NETWORK))

  implementation(project(M.EXTENSIONS))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Androidx.AppCompat.appcompat)
  api(D.Androidx.Work.workRuntimeKtx)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)
  implementation(D.Com.Google.Firebase.firebaseAnalytics)
  implementation(D.Com.Google.Firebase.firebaseIid)
  implementation(D.Com.Google.Firebase.firebaseMessaging)

  testImplementation(D.Androidx.Test.rules)
  testImplementation(D.Androidx.Test.runner)
  testImplementation(D.Androidx.Test.Espresso.espressoCore)
  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.runner)
  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
