import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("dagger.hilt.android.plugin")
  id("kotlin-kapt")
}

dependencies {

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

  implementation(project(Modules.EXTENSIONS))
  implementation(project(Modules.DATABASE))
  implementation(project(Modules.DOMAIN))
  implementation(project(Modules.DTO))

  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)
//  implementation(Dependencies.Com.Google.Firebase.firebaseAnalytics)
  implementation(Dependencies.Com.Google.Firebase.firebaseIid)
  implementation(Dependencies.Com.Google.Firebase.firebaseMessaging)
  implementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)

  testImplementation(Dependencies.Com.Google.Truth.truth)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}