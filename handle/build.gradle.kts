import com.moonlitdoor.amessage.dependencies.Dependencies
import com.moonlitdoor.amessage.dependencies.Modules

plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("dagger.hilt.android.plugin")
  id("kotlin-kapt")
}

android {
  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Dependencies.Androidx.Compose.version
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

  implementation(project(Modules.COMPONENTS))
  implementation(project(Modules.DOMAIN))
//  implementation(project(M.EXPERIMENTS))
//  implementation(project(M.EXTENSIONS))
//  implementation(project(M.HANDLE))
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
  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}
