plugins {
  id("com.moonlitdoor.git-version")
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
  id("androidx.navigation.safeargs")
  id("com.google.firebase.appdistribution")
  id("com.github.triplet.play")
//  id("com.google.firebase.firebase-perf")
//  id("io.fabric")
}

base {
  archivesBaseName = project.name + "-" + gitVersion
}

println("WEB_CLIENT: ${property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_DEFAULT_WEB_CLIENT_ID").toString().substring(0, 9)}")
println("DATABASE_URL: ${property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_FIREBASE_DATABASE_URL").toString().substring(0, 9)}")
println("SENDER: ${property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GCM_DEFAULT_SENDER_ID").toString().substring(0, 9)}")
println("KEY: ${property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_API_KEY").toString().substring(0, 5)}")
println("APP_ID: ${property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_APP_ID").toString().substring(0, 9)}")
println("CRASH: ${property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_CRASH_REPORTING_API_KEY").toString().substring(0, 9)}")
println("STORAGE: ${property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_STORAGE_BUCKET").toString().substring(0, 9)}")
println("APP_ID: ${property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE").toString().substring(0, 9)}")
println("APP_ID: ${property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_STORE_PASSWORD").toString().substring(0, 5)}")
println("APP_ID: ${property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_KEY_ALIAS").toString().substring(0, 5)}")
println("APP_ID: ${property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_KEY_PASSWORD").toString().substring(0, 5)}")
println("VERSION NAME: $gitVersion")
println("VERSION CODE: ${project.extensions.getByName("gitCommitAndTagCount")}")

play {
  serviceAccountCredentials = file("../build/keys/play-api-key.json")
  defaultToAppBundles = true
  track = "alpha"
}

android {
  compileSdkVersion(COMPILE_SDK_VERSION)

  lintOptions {
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
    applicationId = "com.moonlitdoor.amessage"
    minSdkVersion(MIN_SDK_VERSION)
    targetSdkVersion(TARGET_SDK_VERSION)
    versionCode = (project.extensions.getByName("gitCommitAndTagCount") as Long).toInt()
    versionName = gitVersion
//    playAccountConfig = playAccountConfigs.defaultAccountConfig
    buildConfigField("String", "BUILD_DATE", "\"0\"")
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
    testInstrumentationRunner = TEST_RUNNER
//    }
    testInstrumentationRunnerArguments = TEST_RUNNER_ARGUMENTS
  }
  signingConfigs {
    create(RELEASE) {
      storeFile = file(property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE").toString())
      storePassword = property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_STORE_PASSWORD").toString()
      keyAlias = property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_KEY_ALIAS").toString()
      keyPassword = property("COM_MOONLITDOOR_AMESSAGE_KEY_STORE_KEY_PASSWORD").toString()
    }
  }
  buildTypes {
    getByName(RELEASE) {
      signingConfig = signingConfigs.getByName(RELEASE)
      isMinifyEnabled = false
      resValue("string", "app_name", "AMessage")
      buildConfigField("String", "BUILD_DATE", "\"${System.currentTimeMillis()}\"")
      resValue("string", "default_web_client_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_DEFAULT_WEB_CLIENT_ID").toString())
      resValue("string", "firebase_database_url", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_FIREBASE_DATABASE_URL").toString())
      resValue("string", "gcm_defaultSenderId", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GCM_DEFAULT_SENDER_ID").toString())
      resValue("string", "google_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_API_KEY").toString())
      resValue("string", "google_app_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_APP_ID").toString())
      resValue("string", "google_crash_reporting_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_CRASH_REPORTING_API_KEY").toString())
      resValue("string", "google_storage_bucket", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_GOOGLE_STORAGE_BUCKET").toString())
//      resValue ("string", "project_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_PROD_PROJECT_ID").toString())
//      resValue ("string", "com.crashlytics.android.build_id", "\"${UUID.randomUUID()}\"")
    }
    create(BETA) {
      matchingFallbacks = listOf("release")
      isDebuggable = false
      signingConfig = signingConfigs.getByName(RELEASE)
      isMinifyEnabled = false
      applicationIdSuffix = ".beta"
      resValue("string", "app_name", "AMessage Beta")
      resValue("string", "default_web_client_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_DEFAULT_WEB_CLIENT_ID").toString())
      resValue("string", "firebase_database_url", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_FIREBASE_DATABASE_URL").toString())
      resValue("string", "gcm_defaultSenderId", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GCM_DEFAULT_SENDER_ID").toString())
      resValue("string", "google_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_API_KEY").toString())
      resValue("string", "google_app_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_APP_ID").toString())
      resValue("string", "google_crash_reporting_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_CRASH_REPORTING_API_KEY").toString())
      resValue("string", "google_storage_bucket", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_STORAGE_BUCKET").toString())
//      resValue ("string", "project_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_PROJECT_ID").toString())
//      resValue ("string", "com.crashlytics.android.build_id", "\"${UUID.randomUUID()}\"")
      firebaseAppDistribution {
        appId = property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_BETA_GOOGLE_APP_ID").toString()
        serviceCredentialsFile = "./build/keys/firebase-api-key.json"
        groups = "beta"
        releaseNotes = "The Release Notes"
      }
    }
    getByName(DEBUG) {
      isMinifyEnabled = false
      applicationIdSuffix = ".debug"
      resValue("string", "app_name", "AMessage Debug")
      resValue("string", "default_web_client_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_DEFAULT_WEB_CLIENT_ID").toString())
      resValue("string", "firebase_database_url", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_FIREBASE_DATABASE_URL").toString())
      resValue("string", "gcm_defaultSenderId", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GCM_DEFAULT_SENDER_ID").toString())
      resValue("string", "google_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_API_KEY").toString())
      resValue("string", "google_app_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_APP_ID").toString())
      resValue("string", "google_crash_reporting_api_key", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_CRASH_REPORTING_API_KEY").toString())
      resValue("string", "google_storage_bucket", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_GOOGLE_STORAGE_BUCKET").toString())
//      resValue ("string", "project_id", property("COM_MOONLITDOOR_AMESSAGE_FIREBASE_DEBUG_PROJECT_ID").toString())
//      resValue ("string", "com.crashlytics.android.build_id", "\"${UUID.randomUUID()}\"")
    }
  }
  packagingOptions {
    exclude("META-INF/proguard/androidx-annotations.pro")
  }
  dataBinding {
    isEnabled = true
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  sourceSets {
    getByName(SOURCE_SET_TEST) {
      java.srcDir(SHARED_TEST_DIR)
    }
    getByName(SOURCE_SET_ANDROID_TEST) {
      java.srcDir(SHARED_TEST_DIR)
    }
  }

}

dependencies {

  kapt(D.androidxRoomRoomCompiler)

  implementation(project(M.ABOUT))
  implementation(project(M.ANALYTICS))
  implementation(project(M.BINDINGS))
  implementation(project(M.COMPONENTS))
  implementation(project(M.CONSTANTS))
  implementation(project(M.CONNECT))
  implementation(project(M.CONNECTION))
  implementation(project(M.CONVERSATION))
  implementation(project(M.DOMAIN))
  implementation(project(M.EXPERIMENTS))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.FEEDBACK))
  implementation(project(M.HANDLE))
  implementation(project(M.HELP))
  implementation(project(M.IDS))
  implementation(project(M.SETTINGS))
  implementation(project(M.WINDOWS))

  implementation(D.androidxAppcompatAppcompat)
  implementation(D.androidxConstraintlayoutConstraintlayout)
  implementation(D.androidxCoreCoreKtx)
  implementation(D.androidxLifecycleLifecycleRuntimeKtx)
  implementation(D.androidxLifecycleLifecycleLivedataKtx)
  implementation(D.androidxLifecycleLifecycleExtensions)
  implementation(D.androidxLifecycleLifecycleViewmodelKtx)
  implementation(D.androidxNavigationNavigationFragmentKtx)
  implementation(D.androidxNavigationNavigationUiKtx)
  implementation(D.androidxWorkWorkRuntimeKtx)

  implementation(D.comGoogleAndroidMaterialMaterial)
  implementation(D.androidxPreferencePreference)
  implementation(D.androidxLegacyLegacyPreferenceV14)
  implementation(D.androidxRecyclerviewRecyclerview)
  implementation(D.androidxLegacyLegacySupportV4)
  implementation(D.comGoogleAndroidGmsPlayServicesVision)
  implementation(D.comGoogleCodeGsonGson)
  implementation(D.comGoogleFirebaseFirebaseConfig)
  implementation(D.comGoogleFirebaseFirebaseCore)
  implementation(D.comGoogleFirebaseFirebaseMessaging)
  implementation(D.comGoogleZxingCore)
  implementation(D.comJakeWhartonTimberTimber)
  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
  implementation(D.orgKoinKoinAndroid)
  implementation(D.orgKoinKoinAndroidxScope)
  implementation(D.orgKoinKoinAndroidxViewmodel)
  implementation(D.androidxTestEspressoEspressoIdlingResource)

  androidTestUtil(D.androidxTestOrchestrator)

  testImplementation(D.androidxTestRunner)
  testImplementation(D.androidxTestRules)
  testImplementation(D.orgRobolectricRobolectric)
  testImplementation(D.androidxRoomRoomTesting)
  testImplementation(D.androidxLegacyLegacySupportCoreUtils)
  testImplementation(D.androidxTestEspressoEspressoCore)
  testImplementation(D.orgKoinKoinTest)
  testImplementation(D.comSquareupOkhttp3MockWebServer)
  testImplementation(D.androidxTestExtJunitKtx)

  androidTestImplementation(D.androidxTestRunner)
  androidTestImplementation(D.androidxTestExtJunitKtx)
  androidTestImplementation(D.androidxTestRules)
  androidTestImplementation(D.androidxRoomRoomTesting)
  androidTestImplementation(D.androidxTestEspressoEspressoCore)
  androidTestImplementation(D.androidxTestEspressoIdlingIdlingConcurrent)

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

