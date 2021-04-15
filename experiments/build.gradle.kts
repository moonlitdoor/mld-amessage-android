import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
//  id("kotlin-kapt")
}

dependencies {

//  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

//  implementation(project(M.RESOURCES))
  implementation(project(Modules.EXTENSIONS))
  implementation(project(Modules.ROOT))

  implementation(Dependencies.Androidx.Preference.preferenceKtx)
  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(Dependencies.Com.Google.Firebase.firebaseConfig)
  implementation(Dependencies.Com.Google.Firebase.firebaseIid)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Com.Google.Truth.truth)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(Dependencies.Com.Google.Truth.truth)

}
