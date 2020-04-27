plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {
  defaultConfig {
    javaCompileOptions {
      annotationProcessorOptions {
        arguments = mapOf("room.schemaLocation" to "$projectDir/schemas", "room.incremental" to "true")
      }
    }
  }
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)
  kapt(D.Androidx.Room.roomCompiler)

  api(project(M.CONSTANTS))

  api(D.Com.Google.Firebase.firebaseDatabase)
  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
  api(D.Androidx.Room.roomKtx)
  implementation(D.Androidx.Room.roomRuntime)
  implementation(D.Androidx.Core.coreKtx)
  implementation(D.Com.Moonlitdoor.SharedPreferenceLiveData.sharedPreferenceLiveData)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesPlayServices)
  implementation(D.Com.JakeWharton.Timber.timber)

  debugImplementation(D.Com.Amitshekhar.Android.debugDb)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
