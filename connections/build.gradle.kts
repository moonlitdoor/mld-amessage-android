plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {

  buildFeatures {
    compose = true
    dataBinding = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = D.Androidx.Compose.version
  }

  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }

}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(D.Androidx.Compose.Ui.ui)
  implementation(D.Androidx.Compose.Ui.uiTooling)
  implementation(D.Androidx.Compose.Material.material)

  implementation(project(M.BINDINGS))
  implementation(project(M.COMPONENTS))
  api(project(M.DOMAIN))
  implementation(project(M.EXPERIMENTS))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.HANDLE))
  implementation(project(M.IDS))
  implementation(project(M.RESOURCES))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)
  implementation(D.Androidx.Navigation.navigationFragmentKtx)
  implementation(D.Androidx.Navigation.navigationUiKtx)
  implementation(D.Androidx.Lifecycle.lifecycleViewmodelKtx)
  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
