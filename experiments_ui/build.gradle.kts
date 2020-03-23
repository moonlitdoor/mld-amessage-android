plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(COMPILE_SDK_VERSION)

  lintOptions {
    disable("RtlEnabled")
    isWarningsAsErrors = true
    isAbortOnError = true
    xmlReport = false
  }

  testOptions {
    unitTests.apply {
      isReturnDefaultValues = true
      isIncludeAndroidResources = true
    }
    execution = TEST_ORCHESTRATOR
    animationsDisabled = true
  }

  defaultConfig {
    minSdkVersion(MIN_SDK_VERSION)
    targetSdkVersion(TARGET_SDK_VERSION)
    testInstrumentationRunner = TEST_RUNNER
    testInstrumentationRunnerArguments = TEST_RUNNER_ARGUMENTS
  }

  buildTypes {
    getByName(RELEASE) {
      isMinifyEnabled = MINIFY
//      isShrinkResources = SHRINK
      proguardFiles(getDefaultProguardFile(PROGUARD_ANDROID_FILE), PROGUARD_FILE)
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    //    compose = true
    dataBinding = true
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  sourceSets {
    getByName(SOURCE_SET_TEST) {
      java.srcDir(SHARED_TEST_DIR)
    }
    getByName(SOURCE_SET_ANDROID_TEST) {
      java.srcDir(SHARED_TEST_DIR)
    }
  }
//  composeOptions {
//    kotlinCompilerVersion = "1.3.61-dev-withExperimentalGoogleExtensions-20200129"
//    kotlinCompilerExtensionVersion = "0.1.0-dev06"
//  }
}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.BINDINGS))
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
