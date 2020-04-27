plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
  kotlin("kapt")
}

android {
  buildFeatures {
    dataBinding = true
  }
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.BINDINGS))
  implementation(project(M.DOMAIN))
  implementation(project(M.COMPONENTS))
  implementation(project(M.CONSTANTS))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.EXPERIMENTS))
  implementation(project(M.RESOURCES))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Androidx.Camera.cameraCamera2)
  implementation(D.Androidx.Camera.cameraCore)
  implementation(D.Androidx.Camera.cameraLifecycle)
  implementation(D.Androidx.Camera.cameraView)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)
  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
  implementation(D.Androidx.Lifecycle.lifecycleViewmodelKtx)
  implementation(D.Androidx.Navigation.navigationFragmentKtx)
  implementation(D.Androidx.Navigation.navigationUiKtx)
  implementation(D.Androidx.Viewpager2.viewpager2)
  implementation(D.Com.Google.Android.Gms.playServicesVision)
  implementation(D.Com.Google.Firebase.firebaseIid)
  implementation(D.Com.Google.Firebase.firebaseMlVision)
  implementation(D.Com.Google.Firebase.firebaseMlVisionBarcodeModel)
  implementation(D.Com.Google.Zxing.core)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)
}
