plugins {
  id("com.android.library")
  id("com.moonlitdoor.android")
}

dependencies {

  api(project(M.ROOT))

  implementation(D.Androidx.AppCompat.appcompat)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

}
