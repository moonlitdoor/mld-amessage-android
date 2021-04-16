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

  kapt(Dependencies.Com.Google.Dagger.hiltCompiler)

  implementation(project(Modules.COMPONENTS))
  implementation(project(Modules.CONSTANTS))
  implementation(project(Modules.DOMAIN))
  implementation(project(Modules.EXTENSIONS))
  implementation(project(Modules.RESOURCES))
  implementation(project(Modules.THEME))

  implementation(Dependencies.Androidx.Camera.cameraCamera2)
  implementation(Dependencies.Androidx.Camera.cameraCore)
  implementation(Dependencies.Androidx.Camera.cameraLifecycle)
  implementation(Dependencies.Androidx.Camera.cameraView)
  implementation(Dependencies.Androidx.Compose.Material.material)
  implementation(Dependencies.Androidx.Compose.Ui.ui)
  implementation(Dependencies.Androidx.Compose.Ui.uiTooling)
  implementation(Dependencies.Androidx.Lifecycle.lifecycleViewmodelCompose)
  implementation(Dependencies.Androidx.Navigation.navigationCompose)
  implementation(Dependencies.Com.Google.Accompanist.accompanistPager)
  implementation(Dependencies.Com.Google.Android.Gms.playServicesVision)
  implementation(Dependencies.Com.Google.Dagger.hiltAndroid)
  implementation(Dependencies.Com.Google.Firebase.firebaseMlVision)
  implementation(Dependencies.Com.Google.Firebase.firebaseMlVisionBarcodeModel)
  implementation(Dependencies.Com.Google.Zxing.core)
  implementation(Dependencies.Com.JakeWharton.Timber.timber)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
}
