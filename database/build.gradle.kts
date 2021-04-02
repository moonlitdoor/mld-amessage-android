import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

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

  implementation(project(Modules.EXTENSIONS))

  implementation(Dependencies.Androidx.Room.roomKtx)
  implementation(Dependencies.Androidx.Room.roomRuntime)
  implementation(Dependencies.Com.Google.Firebase.firebaseDatabase)
  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
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
