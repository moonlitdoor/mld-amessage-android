package com.moonlitdoor.amessage.dependencies

@Suppress("unused")
object Dependencies {
  object AGP {
    const val gradle = "com.android.tools.build:gradle:7.1.0-alpha03"
  }

  object Androidx {
    object Activity {
      const val activityCompose = "androidx.activity:activity-compose:1.3.0-rc02"
    }

    object Camera {
      private const val version = "1.1.0-alpha06"
      private const val versionView = "1.0.0-alpha26"
      const val cameraCamera2 = "androidx.camera:camera-camera2:$version"
      const val cameraCore = "androidx.camera:camera-core:$version"
      const val cameraLifecycle = "androidx.camera:camera-lifecycle:$version"
      const val cameraView = "androidx.camera:camera-view:$versionView"
    }

    object Compose {
      const val version = "1.0.0-rc02"

      object Material {
        const val material = "androidx.compose.material:material:$version"
      }

      object Ui {
        const val ui = "androidx.compose.ui:ui:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
      }
    }

    object ConstraintLayout {
      const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha08"
    }

    object Core {
      const val coreKtx = "androidx.core:core-ktx:1.6.0-alpha01"
    }

    object Hilt {
      const val hilt = "androidx.hilt:hilt:1.0.0-beta01"
      const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
      const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
      const val hiltWork = "androidx.hilt:hilt-work:1.0.0"
    }

    object Lifecycle {
      const val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
    }

    object Navigation {
      const val navigationCompose = "androidx.navigation:navigation-compose:2.4.0-alpha04"
    }

    object Preference {
      const val preferenceKtx = "androidx.preference:preference-ktx:1.1.1"
    }

    object Room {
      private const val version = "2.4.0-alpha03"
      const val roomCompiler = "androidx.room:room-compiler:$version"
      const val roomRuntime = "androidx.room:room-runtime:$version"
      const val roomKtx = "androidx.room:room-ktx:$version"
      const val roomTesting = "androidx.room:room-testing:$version"
    }

    object Test {
      private const val version = "1.4.0"
      const val core = "androidx.test:core:$version"
      const val orchestrator = "androidx.test:orchestrator:$version"
      const val rules = "androidx.test:rules:$version"
      const val runner = "androidx.test:runner:$version"

      object Espresso {
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0-alpha05"
      }

      object Ext {
        const val junitKtx = "androidx.test.ext:junit-ktx:1.1.3"
      }
    }

    object Work {
      const val workRuntimeKtx = "androidx.work:work-runtime-ktx:2.7.0-alpha04"
    }
  }

  object Com {
    object Google {
      object Accompanist {
        private const val version = "0.14.0"
        const val accompanistInsets = "com.google.accompanist:accompanist-insets:$version"
        const val accompanistPager = "com.google.accompanist:accompanist-pager:$version"
        const val accompanistPagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"
        const val accompanistPermissions = "com.google.accompanist:accompanist-permissions:$version"
      }

      object Android {
        object Gms {
          const val playServicesVision = "com.google.android.gms:play-services-vision:20.1.3"
        }
      }

      object Code {
        object Gson {
          const val gson = "com.google.code.gson:gson:2.8.7"
        }
      }

      object Crypto {
        object Tink {
          const val tinkAndroid = "com.google.crypto.tink:tink-android:1.6.1"
        }
      }

      object Dagger {
        private const val version = "2.38"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
      }

      object Firebase {
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:19.0.0"
        const val firebaseConfig = "com.google.firebase:firebase-config-ktx:21.0.0"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx:18.1.0"
        const val firebaseDatabase = "com.google.firebase:firebase-database-ktx:20.0.0"
        const val firebaseIid = "com.google.firebase:firebase-iid:21.1.0"
        const val firebaseInAppMessaging = "com.google.firebase:firebase-inappmessaging-display-ktx:19.1.1"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:22.0.0"
        const val firebaseMlVision = "com.google.firebase:firebase-ml-vision:24.1.0"
        const val firebaseMlVisionBarcodeModel = "com.google.firebase:firebase-ml-vision-barcode-model:16.1.2"
        const val firebasePerf = "com.google.firebase:firebase-perf:19.0.9"
      }

      object Truth {
        const val truth = "com.google.truth:truth:1.1.3"
      }

      object Zxing {
        const val core = "com.google.zxing:core:3.4.1"
      }
    }

    object JakeWharton {
      object Timber {
        const val timber = "com.jakewharton.timber:timber:4.7.1"
      }
    }

    object MoonlitDoor {
      object AMessage {
        const val dependencies = "com.moonlitdoor.amessage:dependencies"
      }
    }

    object SquareUp {
      object Leakcanary {
        const val leakcanaryAndroid = "com.squareup.leakcanary:leakcanary-android:2.7"
      }

      object OkHttp3 {
        private const val version = "5.0.0-alpha.2"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
      }

      object Retrofit2 {
        private const val version = "2.9.0"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
      }
    }
  }

  object Junit {
    const val junit = "junit:junit:4.13.2"
  }

  object Org {
    object Jetbrains {
      object Kotlin {
        private const val version = "1.4.31"
        const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
      }

      object Kotlinx {
        private const val version = "1.5.1"
        const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val kotlinxCoroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
      }
    }

    object Robolectric {
      const val robolectric = "org.robolectric:robolectric:4.6.1"
    }
  }
}
