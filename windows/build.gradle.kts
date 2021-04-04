plugins {
  id("com.android.dynamic-feature")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {

  dataBinding {
    isEnabled = true
  }
//  buildFeatures {
//    dataBinding = true
//  }

  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }

}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.AMESSAGE))

//  implementation(project(M.COMPONENTS))
//  implementation(project(M.DOMAIN))
//  implementation(project(M.EXTENSIONS))
//  implementation(project(M.IDS))
//  implementation(project(M.RESOURCES))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)
  implementation(D.Androidx.Navigation.navigationFragmentKtx)
  implementation(D.Androidx.Navigation.navigationUiKtx)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
