import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("dagger.hilt.android.plugin")
  id("kotlin-kapt")
}

dependencies {

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)
  kapt(Dependencies.Androidx.Hilt.hiltCompiler)

  implementation(project(Modules.DATABASE))
  implementation(project(Modules.DTO))
  implementation(project(Modules.NETWORK))

  implementation(Dependencies.Androidx.Hilt.hiltWork)
  implementation(Dependencies.Androidx.Work.workRuntimeKtx)
  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)
  implementation(Dependencies.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  implementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

//  implementation(project(M.EXTENSIONS))

//  implementation(D.Com.Google.Dagger.dagger)
//  implementation(D.Androidx.AppCompat.appcompat)
//  api(D.Androidx.Work.workRuntimeKtx)
//  implementation(D.Com.JakeWharton.Timber.timber)
//  implementation(D.Com.Google.Firebase.firebaseAnalytics)
//  implementation(D.Com.Google.Firebase.firebaseIid)
//  implementation(D.Com.Google.Firebase.firebaseMessaging)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}
