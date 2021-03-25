import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("dagger.hilt.android.plugin")
  id("kotlin-kapt")
}

android {

  lint {
    isAbortOnError = false
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.0.0-beta02"
  }

  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }

}

dependencies {

//  kapt(D.Com.Google.Dagger.daggerCompiler)
  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

  implementation(Dependencies.Androidx.Compose.Material.material)
  implementation(Dependencies.Androidx.Compose.Ui.ui)
  implementation(Dependencies.Androidx.Compose.Ui.uiTooling)
  implementation(Dependencies.Androidx.Lifecycle.lifecycleViewmodelCompose)
  implementation(Dependencies.Androidx.Navigation.navigationCompose)
  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)

//  testImplementation(Dependencies.Junit.junit)

//  implementation(project(M.BINDINGS))
//  implementation(project(M.COMPONENTS))
  implementation(project(Modules.DOMAIN))
//  implementation(project(M.EXPERIMENTS))
//  implementation(project(M.EXTENSIONS))
//  implementation(project(M.HANDLE))
//  implementation(project(M.IDS))
//  implementation(project(M.RESOURCES))
  implementation(project(Modules.THEME))
//
//  implementation(D.Com.Google.Dagger.dagger)
//  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)
//  implementation(D.Androidx.ConstraintLayout.constraintLayout)
//  implementation(D.Androidx.Navigation.navigationFragmentKtx)
//  implementation(D.Androidx.Navigation.navigationUiKtx)
//  implementation(D.Androidx.Lifecycle.lifecycleViewmodelKtx)
//  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
//
//  testImplementation(D.Androidx.Test.Ext.junitKtx)
//  testImplementation(D.Org.Robolectric.robolectric)
//
//  androidTestUtil(D.Androidx.Test.orchestrator)
//
//  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
//  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

//  testImplementation(Dependencies.Androidx.Test.runner)
//  testImplementation(Dependencies.Androidx.Test.rules)
  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
//  testImplementation(Dependencies.Androidx.Test.Espresso.espressoCore)
//  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestImplementation(Dependencies.Androidx.Test.runner)
//  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
//  androidTestImplementation(Dependencies.Androidx.Test.Espresso.espressoCore)

}
