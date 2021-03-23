import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("dagger.hilt.android.plugin")
  id("kotlin-kapt")
}

dependencies {

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

  implementation(project(Modules.DOMAIN))

  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)

  implementation(Dependencies.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  implementation(Dependencies.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

  testImplementation(Dependencies.Androidx.Test.runner)
  testImplementation(Dependencies.Androidx.Test.rules)
  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Androidx.Test.Espresso.espressoCore)

  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(Dependencies.Androidx.Test.Espresso.espressoCore)

}
