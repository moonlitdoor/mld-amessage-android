import com.moonlitdoor.android.AndroidPlugin

plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {
  buildTypes {
    getByName(AndroidPlugin.RELEASE) {
      buildConfigField("String", "BASE_URL", "\"https://amessage.moonlitdoor.com\"")
    }
    getByName(AndroidPlugin.DEBUG) {
      buildConfigField("String", "BASE_URL", "\"https://beta.amessage.moonlitdoor.com\"")
    }
  }
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.ENCRYPTION))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Com.SquareUp.OkHttp3.okhttp)
  api(D.Com.SquareUp.OkHttp3.loggingInterceptor)
  implementation(D.Com.SquareUp.Retrofit2.converterGson)
  api(D.Com.SquareUp.Retrofit2.retrofit)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

  kaptTest(D.Com.Google.Dagger.daggerCompiler)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Com.SquareUp.OkHttp3.mockWebServer)
  testImplementation(D.Org.Robolectric.robolectric)
  testImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  testImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Com.SquareUp.OkHttp3.mockWebServer)
  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  androidTestImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

}
