import com.moonlitdoor.amessage.dependencies.Dependencies

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("dagger.hilt.android.plugin")
  id("kotlin-kapt")
}

android {
  defaultConfig {
    javaCompileOptions {
      annotationProcessorOptions {
        arguments.putAll(mapOf("room.schemaLocation" to "$projectDir/schemas"))
      }
    }
  }
}

dependencies {

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)
  kapt(Dependencies.Androidx.Room.roomCompiler)

//  api(project(M.CONSTANTS))

  implementation(Dependencies.Com.Google.Firebase.firebaseDatabase)
  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
//  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
  implementation(Dependencies.Androidx.Room.roomKtx)
  implementation(Dependencies.Androidx.Room.roomRuntime)
//  implementation(D.Androidx.Core.coreKtx)
//  implementation(D.Com.Moonlitdoor.SharedPreferenceLiveData.sharedPreferenceLiveData)
//  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
//  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
//  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)
//  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesPlayServices)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)

//  debugImplementation(D.Com.Amitshekhar.Android.debugDb)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}
