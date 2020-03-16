object D {
  object Androidx {
//    object Annotation {
//      const val annotation = "androidx.annotation:annotation:1.1.0"
//    }

    object AppCompat {
      const val appcompat = "androidx.appcompat:appcompat:1.2.0-alpha03"
    }

    object Camera {
      private const val version = "1.0.0-beta01"
      private const val versionView = "1.0.0-alpha08"
      const val cameraCamera2 = "androidx.camera:camera-camera2:$version"
      const val cameraCore = "androidx.camera:camera-core:$version"
      const val cameraLifecycle = "androidx.camera:camera-lifecycle:$version"
      const val cameraView = "androidx.camera:camera-view:$versionView"
    }

    object Compose {
      const val composeRuntime = "androidx.compose:compose-runtime:0.1.0-dev06"
    }

    object ConstraintLayout {
      const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
    }

    object Core {
      const val coreKtx = "androidx.core:core-ktx:1.3.0-alpha02"
    }

    object Fragment {
      private const val version = "1.3.0-alpha01"
      const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
      const val fragmentTesting = "androidx.fragment:fragment-testing:$version"
    }

    object Legacy {
      const val legacyPreferenceV14 = "androidx.legacy:legacy-preference-v14:1.0.0"
      const val legacySupportCoreUtils = "androidx.legacy:legacy-support-core-utils:1.0.0"
      const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"
    }

    object Lifecycle {
      private const val versionOld = "2.2.0"
      private const val version = "2.3.0-alpha01"
      const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$versionOld"
      const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
      const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
      const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    }

    object Navigation {
      private const val version = "2.3.0-alpha03"
      const val navigationDynamicFeatureFragment = "androidx.navigation:navigation-dynamic-features-fragment:$version"
      const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
      const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
      const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"
    }

    object Preference {
      const val preference = "androidx.preference:preference:1.1.0"
    }

    object Recyclerview {
      const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha01"
    }

    object Room {
      private const val version = "2.2.4"
      const val roomCompiler = "androidx.room:room-compiler:$version"
      const val roomRuntime = "androidx.room:room-runtime:$version"
      const val roomKtx = "androidx.room:room-ktx:$version"
      const val roomTesting = "androidx.room:room-testing:$version"
    }

    object Test {
      private const val version = "1.3.0-alpha04"
      const val core = "androidx.test:core:$version"
      const val orchestrator = "androidx.test:orchestrator:$version"
      const val rules = "androidx.test:rules:$version"
      const val runner = "androidx.test:runner:$version"

      object Espresso {
        private const val version = "3.3.0-alpha04"
        const val espressoCore = "androidx.test.espresso:espresso-core:$version"
        const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:$version"

        object Idling {
          const val idlingConcurrent = "androidx.test.espresso.idling:idling-concurrent:$version"
        }
      }

      object Ext {
        const val junitKtx = "androidx.test.ext:junit-ktx:1.1.2-alpha04"
      }
    }

    object Ui {
      private const val version = "0.1.0-dev06"
      const val uiLayout = "androidx.ui:ui-layout:$version"
      const val uiMaterial = "androidx.ui:ui-material:$version"
      const val uiTooling = "androidx.ui:ui-tooling:$version"
    }

    object Viewpager2 {
      const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    }

    object Work {
      const val workRuntimeKtx = "androidx.work:work-runtime-ktx:2.4.0-alpha01"
    }
  }

  object Com {

    object Google {
      object Android {
        object Gms {
          const val playServicesVision = "com.google.android.gms:play-services-vision:19.0.0"
        }

        object Material {
          const val material = "com.google.android.material:material:1.2.0-alpha05"
        }
      }

      object Code {
        object Gson {
          const val gson = "com.google.code.gson:gson:2.8.6"
        }
      }

      object Dagger {
        private const val version = "2.26"
        const val dagger = "com.google.dagger:dagger:$version"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
      }

      object Firebase {
        const val firebaseAppdistributionGradle = "com.google.firebase:firebase-appdistribution-gradle:1.3.1"
        const val firebaseConfig = "com.google.firebase:firebase-config:19.1.2"
        const val firebaseCore = "com.google.firebase:firebase-core:17.2.3"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.0.0-beta01"
        const val firebaseMessaging = "com.google.firebase:firebase-messaging:20.1.2"
        const val firebaseMlVision = "com.google.firebase:firebase-ml-vision:24.0.1"
        const val firebaseMlVisionBarcodeModel = "com.google.firebase:firebase-ml-vision-barcode-model:16.0.2"
        const val firebasePerf = "com.google.firebase:firebase-perf:19.0.5"
      }

      object Truth {
        const val truth = "com.google.truth:truth:1.0.1"
      }

      object Zxing {
        const val core = "com.google.zxing:core:3.4.0"
      }
    }

    object JakeWharton {
      object Timber {
        const val timber = "com.jakewharton.timber:timber:4.7.1"
      }
    }

    object Moonlitdoor {
      object SharedPreferenceLiveData {
        const val sharedPreferenceLiveData = "com.moonlitdoor.shared-preference-live-data:shared-preference-live-data:0.0.6"
      }
    }

    object SquareUp {
      object OkHttp3 {
        private const val version = "4.4.0"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
      }

      object Retrofit2 {
        private const val version = "2.7.1"
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
    const val junit = "junit:junit:4.13"
  }

  object Org {
    object Jetbrains {
      object Kotlin {
        private const val version = "1.3.70"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
      }

      object Kotlinx {
        private const val version = "1.3.4"
        const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
      }
    }

    object Robolectric {
      const val robolectric = "org.robolectric:robolectric:4.3.1"
    }
  }
}
