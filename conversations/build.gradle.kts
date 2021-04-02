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
    kotlinCompilerExtensionVersion = Dependencies.Androidx.Compose.version
  }

  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }

}

dependencies {

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

  implementation(project(Modules.COMPONENTS))
  implementation(project(Modules.DOMAIN))
  implementation(project(Modules.ROUTES))
  implementation(project(Modules.THEME))

  implementation(Dependencies.Androidx.Compose.Material.material)
  implementation(Dependencies.Androidx.Compose.Ui.ui)
  implementation(Dependencies.Androidx.Compose.Ui.uiTooling)
  implementation(Dependencies.Androidx.Lifecycle.lifecycleViewmodelCompose)
  implementation(Dependencies.Androidx.Navigation.navigationCompose)
  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)

//  testImplementation(Dependencies.Junit.junit)

//  implementation(project(M.BINDINGS))
//  implementation(project(M.EXPERIMENTS))
//  implementation(project(M.EXTENSIONS))
//  implementation(project(M.HANDLE))
//  implementation(project(M.IDS))
//  implementation(project(M.RESOURCES))

//  implementation(D.Com.Google.Dagger.dagger)
//  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
//  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
//  implementation(D.Androidx.ConstraintLayout.constraintLayout)
//  implementation(D.Androidx.Navigation.navigationFragmentKtx)
//  implementation(D.Androidx.Navigation.navigationUiKtx)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
}
