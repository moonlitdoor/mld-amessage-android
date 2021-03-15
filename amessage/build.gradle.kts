import com.moonlitdoor.android.AndroidPlugin

plugins {
//  id("com.moonlitdoor.git-version")
  id("com.android.application")
//  id("com.moonlitdoor.android")
//  id("kotlin-kapt")
  id("androidx.navigation.safeargs")
  id("com.google.firebase.appdistribution")
  id("com.github.triplet.play")
  id("com.google.firebase.crashlytics")

  if (System.getenv("COM_MOONLITDOOR_AMESSAGE_PERF") != null) {
    println("Applying Performance Plugin")
    id("com.google.firebase.firebase-perf")
  }
}

base {
  archivesBaseName = "$name-$gitVersion"
}

println("VERSION NAME: $gitVersion")
println("VERSION CODE: ${project.extensions.getByName("gitCommitAndTagCount")}")

play {
  serviceAccountCredentials = file("../build/keys/playstore-api-key.json")
  defaultToAppBundles = true
  track = "alpha"
}

android {
//  dynamicFeatures = mutableSetOf(M.ABOUT, M.FEEDBACK, M.HELP, M.WINDOWS)

  lintOptions {
    isAbortOnError = false
    disable(
      "NullSafeMutableLiveData",
      "InvalidFragmentVersionForActivityResult"
    )
    isIgnoreTestSources = true
    isCheckDependencies = true
  }

  defaultConfig {
    applicationId = "com.moonlitdoor.amessage"
    versionCode = (project.extensions.getByName("gitCommitAndTagCount") as Long).toInt()
    versionName = gitVersion
    resValue("color", "launcher_background", "@color/purple_deep_A400")
    buildConfigField("String", "BUILD_DATE", "\"0\"")
    buildConfigField("boolean", "USE_COMPOSE", "true")
//    if (largeTests) {
//      testInstrumentationRunner "com.moonlitdoor.amessage.AndroidJUnitRunnerLarge"
//    } else if (mediumTests) {
//      testInstrumentationRunner "com.moonlitdoor.amessage.AndroidJUnitRunnerMedium"
//    } else if (smallTests) {
//      testInstrumentationRunner "com.moonlitdoor.amessage.AndroidJUnitRunnerSmall"
//    } else if (smokeTests) {
//      testInstrumentationRunner "com.moonlitdoor.amessage.AndroidJUnitRunnerSmoke"
//    } else if (flakyTests) {
//      testInstrumentationRunner "com.moonlitdoor.amessage.AndroidJUnitRunnerFlaky"
//    } else {
//    }
  }
  signingConfigs {
    create(AndroidPlugin.RELEASE) {
      storeFile = file(property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE").toString())
      storePassword = property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_STORE_PASSWORD").toString()
      keyAlias = property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_KEY_ALIAS").toString()
      keyPassword = property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_KEY_PASSWORD").toString()
    }
  }
  buildTypes {
    getByName(AndroidPlugin.RELEASE) {
      signingConfig = signingConfigs.getByName(AndroidPlugin.RELEASE)
      isShrinkResources = false
      proguardFiles(getDefaultProguardFile(AndroidPlugin.PROGUARD_ANDROID_FILE), AndroidPlugin.PROGUARD_FILE)
      resValue("string", "app_name", "AMessage")
      buildConfigField("String", "BUILD_DATE", "\"${System.currentTimeMillis()}\"")
      resValue("string", "default_web_client_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_DEFAULT_WEB_CLIENT_ID").toString())
      resValue("string", "firebase_database_url", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_FIREBASE_DATABASE_URL").toString())
      resValue("string", "gcm_defaultSenderId", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GCM_DEFAULT_SENDER_ID").toString())
      resValue("string", "google_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_API_KEY").toString())
      resValue("string", "google_app_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_APP_ID").toString())
      resValue("string", "google_crash_reporting_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_CRASH_REPORTING_API_KEY").toString())
      resValue("string", "google_storage_bucket", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_STORAGE_BUCKET").toString())
      resValue("string", "project_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_PROJECT_ID").toString())
    }

    create(AndroidPlugin.BETA) {
      matchingFallbacks += AndroidPlugin.RELEASE
      isDebuggable = false
      signingConfig = signingConfigs.getByName(AndroidPlugin.RELEASE)
      isShrinkResources = false
      proguardFiles(getDefaultProguardFile(AndroidPlugin.PROGUARD_ANDROID_FILE), AndroidPlugin.PROGUARD_FILE)
      applicationIdSuffix = ".beta"
      resValue("color", "launcher_background", "@color/colorMonsterAccent")
      resValue("string", "app_name", "AMessage Beta")
      resValue("string", "default_web_client_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_DEFAULT_WEB_CLIENT_ID").toString())
      resValue("string", "firebase_database_url", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_FIREBASE_DATABASE_URL").toString())
      resValue("string", "gcm_defaultSenderId", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GCM_DEFAULT_SENDER_ID").toString())
      resValue("string", "google_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_API_KEY").toString())
      resValue("string", "google_app_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_APP_ID").toString())
      resValue("string", "google_crash_reporting_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_CRASH_REPORTING_API_KEY").toString())
      resValue("string", "google_storage_bucket", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_STORAGE_BUCKET").toString())
      resValue("string", "project_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_PROJECT_ID").toString())
      firebaseAppDistribution {
        apkPath = "./amessage/build/outputs/apk/beta/amessage-$gitVersion-beta.apk"
        appId = property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_APP_ID").toString()
        serviceCredentialsFile = "./build/keys/firebase-api-key.json"
        groups = "beta"
        releaseNotes = "The Release Notes"
      }
    }

    getByName(AndroidPlugin.DEBUG) {
      isMinifyEnabled = false
      applicationIdSuffix = ".debug"
      resValue("color", "launcher_background", "@color/colorDarkPrimary")
      resValue("string", "app_name", "AMessage Debug")
      resValue("string", "default_web_client_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_DEFAULT_WEB_CLIENT_ID").toString())
      resValue("string", "firebase_database_url", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_FIREBASE_DATABASE_URL").toString())
      resValue("string", "gcm_defaultSenderId", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GCM_DEFAULT_SENDER_ID").toString())
      resValue("string", "google_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_API_KEY").toString())
      resValue("string", "google_app_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_APP_ID").toString())
      resValue("string", "google_crash_reporting_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_CRASH_REPORTING_API_KEY").toString())
      resValue("string", "google_storage_bucket", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_STORAGE_BUCKET").toString())
      resValue("string", "project_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_PROJECT_ID").toString())
    }
  }

  packagingOptions {
    exclude("META-INF/proguard/androidx-annotations.pro")
  }

  buildFeatures {
//    compose = true
    dataBinding = true
  }

//  composeOptions {
//    kotlinCompilerExtensionVersion = D.Androidx.Compose.version
//  }

  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }

}

dependencies {

//  kapt(D.Com.Google.Dagger.daggerCompiler)

//  implementation(D.Androidx.Compose.Ui.ui)
//  implementation(D.Androidx.Compose.Ui.uiTooling)
//  implementation(D.Androidx.Compose.Material.material)

//  implementation(project(M.ANALYTICS))
//  api(project(M.BINDINGS))
//  api(project(M.COMPONENTS))
//  implementation(project(M.CONSTANTS))
//  api(project(M.CONNECT))
//  api(project(M.CONNECTIONS))
//  api(project(M.CONVERSATIONS))
//  api(project(M.DOMAIN))
//  api(project(M.EXPERIMENTS))
//  api(project(M.EXTENSIONS))
//  api(project(M.HANDLE))
//  implementation(project(M.IDS))
//  api(project(M.PUSH))
//  api(project(M.RESOURCES))
//  implementation(project(M.ROOT))
//  api(project(M.SETTINGS))

//  implementation(D.Androidx.Lifecycle.lifecycleServiceKtx)
//  implementation(D.Com.Google.Dagger.dagger)
//  implementation(D.Androidx.AppCompat.appcompat)
//  implementation(D.Androidx.Camera.cameraCamera2)
//  implementation(D.Androidx.Camera.cameraCore)
  implementation(D.Androidx.Core.coreKtx)
//  implementation(D.Androidx.Fragment.fragmentKtx)
//  implementation(D.Androidx.Lifecycle.lifecycleRuntimeKtx)
//  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
//  implementation(D.Androidx.Lifecycle.lifecycleViewmodelKtx)
  implementation(D.Androidx.Navigation.navigationDynamicFeatureFragment)
  implementation(D.Androidx.Navigation.navigationFragmentKtx)
  implementation(D.Androidx.Navigation.navigationUiKtx)

//  implementation(D.Androidx.Preference.preference)
//  implementation(D.Androidx.Legacy.legacyPreferenceV14)
//  implementation(D.Androidx.Recyclerview.recyclerview)
//  implementation(D.Androidx.Legacy.legacySupportV4)
//  implementation(D.Com.Google.Android.Gms.playServicesVision)
//  implementation(D.Com.Google.Code.Gson.gson)
//  implementation(D.Com.Google.Firebase.firebaseConfig)
//  implementation(D.Com.Google.Firebase.firebaseAnalytics)
//  implementation(D.Com.Google.Firebase.firebaseIid)
//  implementation(D.Com.Google.Firebase.firebaseInAppMessaging)
//  implementation(D.Com.Google.Firebase.firebaseMessaging)
//  implementation(D.Com.Google.Firebase.firebasePerf)
//  implementation(D.Com.Google.Firebase.firebaseCrashlytics)
//  implementation(D.Com.Google.Guava.listenablefuture)
//  implementation(D.Com.Google.Zxing.core)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
//  implementation(D.Androidx.Test.Espresso.espressoIdlingResource)

  androidTestUtil(D.Androidx.Test.orchestrator)

  testImplementation(D.Androidx.Test.runner)
  testImplementation(D.Androidx.Test.rules)
  testImplementation(D.Org.Robolectric.robolectric)
//  testImplementation(D.Androidx.Room.roomTesting)
//  testImplementation(D.Androidx.Legacy.legacySupportCoreUtils)
//  testImplementation(D.Androidx.Test.Espresso.espressoCore)
//  testImplementation(D.Com.SquareUp.OkHttp3.mockWebServer)
  testImplementation(D.Androidx.Test.Ext.junitKtx)

  androidTestImplementation(D.Androidx.Test.runner)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(D.Androidx.Test.rules)
//  androidTestImplementation(D.Androidx.Room.roomTesting)
  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
//  androidTestImplementation(D.Androidx.Test.Espresso.Idling.idlingConcurrent)

}

//task connectedLargeTests {
//  dependsOn "connectedCheck"
//  doFirst {
//
//  }
//}

//apply plugin: "com.google.gms.google-services"

//task parseMetadata() {
//  ext.parse = { param1, param2 ->
//    rootProject.file(String.format("amessage/src/main/play/%s", param1)).mkdir()
//    def useDefault = true
//    def file = rootProject.file(String.format("amessage/src/main/play/%s/whatsnew", param1))
//    file.delete()
//    def parsedStrings = new XmlParser().parse(rootProject.file(String.format("amessage/src/main/res/%s/strings.xml", param2)))
//    parsedStrings["string-array"].each { stringArray ->
//      if (stringArray.@name == "metadata_whats_new") {
//        stringArray.item.each { item ->
//          useDefault = false
//          file.append(item.text().replace("\\", "") + "\n")
//        }
//      }
//    }
//    rootProject.file(String.format("amessage/src/main/play/%s/listing", param1)).mkdir()
//    parsedStrings.string.each { string ->
//      if (string.@name == "metadata_title") {
//        file = rootProject.file(String.format("amessage/src/main/play/%s/listing/title", param1))
//        file.delete()
//        file.append(string.text().replace("\\", ""))
//      } else if (string.@name == "metadata_short_description") {
//        file = rootProject.file(String.format("amessage/src/main/play/%s/listing/shortdescription", param1))
//        file.delete()
//        file.append(string.text().replace("\\", ""))
//      } else if (string.@name == "metadata_full_description") {
//        file = rootProject.file(String.format("amessage/src/main/play/%s/listing/fulldescription", param1))
//        file.delete()
//        file.append(string.text().replace("\\", ""))
//      } else if (string.@name == "metadata_whats_new_bug_fixes" && useDefault) {
//        file = rootProject.file(String.format("amessage/src/main/play/%s/whatsnew", param1))
//        file.delete()
//        file.append(string.text().replace("\\", ""))
//      }
//    }
//  }
//  doLast {
//    parse("en-US", "values")
//  }
//}

//project.afterEvaluate {
//  project.tasks.getByName("generateReleasePlayResources").dependsOn parseMetadata
//}

