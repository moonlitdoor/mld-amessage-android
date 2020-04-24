plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
}

android {
  buildFeatures {
    dataBinding = true
  }
}

dependencies {

  implementation(project(M.BINDINGS))
  implementation(project(M.CONSTANTS))
  implementation(project(M.DOMAIN))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.RESOURCES))

  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Androidx.AppCompat.appcompat)
  implementation(D.Com.Google.Android.Material.material)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
