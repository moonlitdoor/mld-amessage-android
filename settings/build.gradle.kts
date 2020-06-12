plugins {
  id("com.android.library")
  id("androidx.navigation.safeargs")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {
  lintOptions {
    disable("WrongConstant")
  }

  buildFeatures {
    dataBinding = true
  }

}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.BINDINGS))
  implementation(project(M.DOMAIN))
  implementation(project(M.EXPERIMENTS))
  api(project(M.EXPERIMENTS_UI))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.COMPONENTS))
  implementation(project(M.CONSTANTS))
  implementation(project(M.RESOURCES))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Androidx.Core.coreKtx)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Com.Google.Android.Gms.playServicesVision)
  implementation(D.Androidx.Navigation.navigationFragmentKtx)
  implementation(D.Androidx.Navigation.navigationUiKtx)
  implementation(D.Com.Google.Zxing.core)
  implementation(D.Com.Google.Firebase.firebaseAnalytics)
  implementation(D.Com.Google.Firebase.firebaseIid)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)
  implementation(D.Androidx.Preference.preference)
  implementation(D.Androidx.Legacy.legacyPreferenceV14)
  implementation(D.Com.JakeWharton.Timber.timber)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
