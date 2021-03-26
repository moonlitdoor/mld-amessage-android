package com.moonlitdoor.amessage.dependencies

object Dependencies {
  object AGP {
    const val gradle = "com.android.tools.build:gradle:7.0.0-alpha12"
  }


  object Androidx {
    const val version = "off"

//    object Annotation {
//      const val annotation = "androidx.annotation:annotation:1.1.0"
//    }

    object Activity {
      const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha05"
    }

    object AppCompat {
      const val appcompat = "androidx.appcompat:appcompat:1.3.0-rc01"
    }

    object Camera {
      private const val version = "1.1.0-alpha03"
      private const val versionView = "1.0.0-alpha23"
      const val cameraCamera2 = "androidx.camera:camera-camera2:$version"
      const val cameraCore = "androidx.camera:camera-core:$version"
      const val cameraLifecycle = "androidx.camera:camera-lifecycle:$version"
      const val cameraView = "androidx.camera:camera-view:$versionView"
    }

    object Compose {
      const val version = "1.0.0-beta03"

      object Material {
        const val material = "androidx.compose.material:material:$version"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
      }

      object Ui {
        const val ui = "androidx.compose.ui:ui:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
      }
    }

    object Core {
      const val coreKtx = "androidx.core:core-ktx:1.6.0-alpha01"
    }

    object Fragment {
      private const val version = "1.3.0-alpha08"
      const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
      const val fragmentTesting = "androidx.fragment:fragment-testing:$version"
    }

    object Hilt {
      const val hilt = "androidx.hilt:hilt:1.0.0-beta01"
      const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha01"
    }

    object Legacy {
      const val legacyPreferenceV14 = "androidx.legacy:legacy-preference-v14:1.0.0"
      const val legacySupportCoreUtils = "androidx.legacy:legacy-support-core-utils:1.0.0"
      const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"
    }

    object Lifecycle {
      private const val version = "2.3.0"
      const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
      const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
      const val lifecycleServiceKtx = "androidx.lifecycle:lifecycle-service:$version"
      const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
      const val lifecycleViewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha03"
    }

    object Navigation {
      private const val version = "2.3.3"
      const val navigationDynamicFeatureFragment = "androidx.navigation:navigation-dynamic-features-fragment:$version"
      const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
      const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
      const val navigationCompose = "androidx.navigation:navigation-compose:1.0.0-alpha09"
    }

    object Preference {
      const val preference = "androidx.preference:preference-ktx:1.1.1"
    }

    object Recyclerview {
      const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha05"
    }

    object Room {
      private const val version = "2.3.0-rc01"
      const val roomCompiler = "androidx.room:room-compiler:$version"
      const val roomRuntime = "androidx.room:room-runtime:$version"
      const val roomKtx = "androidx.room:room-ktx:$version"
      const val roomTesting = "androidx.room:room-testing:$version"
    }

    object Test {
      private const val version = "1.3.0"
      const val core = "androidx.test:core:$version"
      const val orchestrator = "androidx.test:orchestrator:$version"
      const val rules = "androidx.test:rules:$version"
      const val runner = "androidx.test:runner:$version"

      object Espresso {
        private const val version = "3.4.0-alpha05"
        const val espressoCore = "androidx.test.espresso:espresso-core:$version"
        const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:$version"

        object Idling {
          const val idlingConcurrent = "androidx.test.espresso.idling:idling-concurrent:$version"
        }
      }

      object Ext {
        const val junitKtx = "androidx.test.ext:junit-ktx:1.1.3-alpha05"
      }
    }

    object Viewpager2 {
      const val viewpager2 = "androidx.viewpager2:viewpager2:1.1.0-alpha01"
    }

    object Work {
      const val workRuntimeKtx = "androidx.work:work-runtime-ktx:2.5.0-alpha02"
    }
  }

  object Com {
    object Amitshekhar {
      object Android {
        const val debugDb = "com.amitshekhar.android:debug-db:1.0.6"
      }
    }

    object Google {
      object Accompanist {
        const val accompanistInsets = "com.google.accompanist:accompanist-insets:0.7.0"
      }

      object Android {
        object Gms {
          const val playServicesVision = "com.google.android.gms:play-services-vision:20.1.3"
        }

        object Material {
          const val material = "com.google.android.material:material:1.3.0-alpha02"
        }
      }

      object Code {
        object Gson {
          const val gson = "com.google.code.gson:gson:2.8.6"
        }
      }

      object Dagger {
        private const val version = "2.33-beta"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:$version"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$version"
      }

      object Firebase {
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:17.5.0"
        const val firebaseConfig = "com.google.firebase:firebase-config-ktx:19.2.0"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.2.2"
        const val firebaseDatabase = "com.google.firebase:firebase-database-ktx:19.7.0"
        const val firebaseIid = "com.google.firebase:firebase-iid:21.0.1"
        const val firebaseInAppMessaging = "com.google.firebase:firebase-inappmessaging-display-ktx:19.1.1"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:20.3.0"
        const val firebaseMlVision = "com.google.firebase:firebase-ml-vision:24.1.0"
        const val firebaseMlVisionBarcodeModel = "com.google.firebase:firebase-ml-vision-barcode-model:16.1.2"
        const val firebasePerf = "com.google.firebase:firebase-perf:19.0.9"
      }

      object Guava {
        const val listenablefuture = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"
      }

      object Truth {
        const val truth = "com.google.truth:truth:1.0.1"
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

      object SharedPreferenceLiveData {
        const val sharedPreferenceLiveData = "com.moonlitdoor.shared-preference-live-data:shared-preference-live-data:0.0.6"
      }
    }

    object SquareUp {
      object OkHttp3 {
        private const val version = "4.9.0"
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

    object Stepstone {
      object Stepper {
        const val materialStepper = "com.stepstone.stepper:material-stepper:4.3.1"
      }
    }
  }

  object Junit {
    const val junit = "junit:junit:4.13.2"
  }

  object Org {
    object Jetbrains {
      object Kotlin {
        private const val version = "1.4.30"
        const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
      }

      object Kotlinx {
        private const val version = "1.3.9"
        const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val kotlinxCoroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
      }
    }

    object Robolectric {
      const val robolectric = "org.robolectric:robolectric:4.5.1"
    }
  }
}