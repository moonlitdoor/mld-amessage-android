import com.moonlitdoor.amessage.dependencies.Dependencies

plugins {
//    id 'com.moonlitdoor.amessage.android'
  id("com.android.application")
  id("kotlin-android")
}

println(Dependencies.Androidx.version)

android {
  compileSdkVersion(30)

  defaultConfig {
    applicationId = "com.moonlitdoor.amessage"
    minSdkVersion(24)
    targetSdkVersion(30)
    versionCode(1)
    versionName("1.0")

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
//      proguardFiles = getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro"
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.0.0-beta02"
//    kotlinCompilerVersion ='1.4.30'
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.3.2")
  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("com.google.android.material:material:1.3.0")
  implementation("androidx.compose.ui:ui:1.0.0-beta02")
  implementation("androidx.compose.material:material:1.0.0-beta02")
  implementation("androidx.compose.ui:ui-tooling:1.0.0-beta02")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0")
  implementation("androidx.activity:activity-compose:1.3.0-alpha04")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.2")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}