import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("dagger.hilt.android.plugin")
  id("kotlin-kapt")
}

dependencies {

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

  implementation(project(Modules.DATABASE))
  implementation(project(Modules.NETWORK))

  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)

//  implementation(project(M.EXTENSIONS))

//  implementation(D.Com.Google.Dagger.dagger)
//  implementation(D.Androidx.AppCompat.appcompat)
//  api(D.Androidx.Work.workRuntimeKtx)
//  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(Dependencies.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  implementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)
//  implementation(D.Com.Google.Firebase.firebaseAnalytics)
//  implementation(D.Com.Google.Firebase.firebaseIid)
//  implementation(D.Com.Google.Firebase.firebaseMessaging)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}
