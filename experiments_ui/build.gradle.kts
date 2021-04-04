plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {
  buildFeatures {
    //    compose = true
    dataBinding = true
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

//  composeOptions {
//    kotlinCompilerVersion = "1.3.61-dev-withExperimentalGoogleExtensions-20200129"
//    kotlinCompilerExtensionVersion = "0.1.0-dev06"
//  }
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.EXTENSIONS))
  implementation(project(M.EXPERIMENTS))
  implementation(project(M.RESOURCES))

  implementation(D.Com.Google.Dagger.dagger)

//  implementation(D.Androidx.Compose.composeRuntime)
//  implementation(D.Androidx.Ui.uiLayout)
//  implementation(D.Androidx.Ui.uiMaterial)
//  implementation(D.Androidx.Ui.uiTooling)

  implementation(D.Androidx.AppCompat.appcompat)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)
  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
  implementation(D.Androidx.Lifecycle.lifecycleViewmodelKtx)
  implementation(D.Androidx.Navigation.navigationFragmentKtx)
  implementation(D.Androidx.Navigation.navigationUiKtx)
  implementation(D.Androidx.Recyclerview.recyclerview)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

  debugImplementation(D.Androidx.Fragment.fragmentTesting)
  debugImplementation(D.Androidx.Test.core)

  testImplementation(D.Androidx.Fragment.fragmentTesting)
  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Fragment.fragmentTesting)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
