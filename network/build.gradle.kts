import com.moonlitdoor.amessage.dependencies.Constants
import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("kotlin-kapt")
}

android {
  buildTypes {
    getByName(Constants.RELEASE) {
      buildConfigField("String", "BASE_URL", "\"https://amessage.moonlitdoor.com\"")
    }
    getByName(Constants.DEBUG) {
      buildConfigField("String", "BASE_URL", "\"https://beta.amessage.moonlitdoor.com\"")
    }
  }
}

dependencies {

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

  implementation(project(Modules.ENCRYPTION))

  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)
  implementation(Dependencies.Com.SquareUp.OkHttp3.okhttp)
  implementation(Dependencies.Com.SquareUp.OkHttp3.loggingInterceptor)
  implementation(Dependencies.Com.SquareUp.Retrofit2.converterGson)
  implementation(Dependencies.Com.SquareUp.Retrofit2.retrofit)
  implementation(Dependencies.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

//  kaptTest(Dependencies.Com.Google.Dagger.hiltCompiler)

//  testImplementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  testImplementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)


//  androidTestImplementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  androidTestImplementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Com.SquareUp.OkHttp3.mockWebServer)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Junit.junit)
//  androidTestImplementation(Dependencies.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(Dependencies.Com.SquareUp.OkHttp3.mockWebServer)

}
