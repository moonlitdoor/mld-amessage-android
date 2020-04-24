package com.moonlitdoor.amessage.constants

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.moonlitdoor.amessage.root.Root
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConstantsTest {

  @Before
  fun setup() {
    Root.init(InstrumentationRegistry.getInstrumentation().targetContext as Application)
  }

  @Test
  fun testExperiment() {
    assertTrue(Constants.EXPERIMENTS.contains("YfZO7iEcaej2mq6ZZdMyfPlhJ4C"))
    assertTrue(Constants.EXPERIMENTS.contains("J4C3i1XCtYH9Sk7bEqrKrRZ0vKZhi5nvXnckc"))
    assertTrue(Constants.EXPERIMENTS.contains("nckc983tpyw8lfRCUPUAzSWBqxhAV9g53wgTb"))
    assertTrue(Constants.EXPERIMENTS.contains("g53wgTbsftnAz9YPNfzZ5dZs9SUAXyygZ"))
  }

  @Test
  fun testBaseUrlFirebase() {
    assertEquals("https://fcm.googleapis.com", Constants.BASE_URL_FIREBASE)
  }

  @Test
  fun testSharedPreferences() {
    assertNotNull(Constants.SharedPreferences)
  }

  @Test
  fun testSharedPreferencesVersionCode() {
    assertEquals("com.moonlitdoor.amessage.version_code", Constants.SharedPreferences.VERSION_CODE)
  }

  @Test
  fun testSharedPreferencesHandle() {
    assertEquals("com.moonlitdoor.amessage.handle", Constants.SharedPreferences.HANDLE)
  }

  @Test
  fun testSharedPreferencesToken() {
    assertEquals("com.moonlitdoor.amessage.token", Constants.SharedPreferences.TOKEN)
  }

  @Test
  fun testSharedPreferencesID() {
    assertEquals("com.moonlitdoor.amessage.id", Constants.SharedPreferences.ID)
  }

  @Test
  fun testSharedPreferencesPassword() {
    assertEquals("com.moonlitdoor.amessage.password", Constants.SharedPreferences.PASSWORD)
  }

  @Test
  fun testSharedPreferencesSalt() {
    assertEquals("com.moonlitdoor.amessage.salt", Constants.SharedPreferences.SALT)
  }

  @Test
  fun testKeysScreens() {
    assertEquals(5, Constants.Keys.Defaults.SCREENS)
    assertEquals("com.moonlitdoor.amessage.screens.count", Constants.Keys.SCREENS)
  }

  @Test
  fun testKeysTheme() {
    assertEquals("com.moonlitdoor.amessage.theme", Constants.Keys.THEME)
    assertEquals("0", Constants.Keys.Defaults.THEME)
  }

  @Test
  fun testKeysStartingLocation() {
    assertEquals("com.moonlitdoor.amessage.starting.location", Constants.Keys.STARTING_LOCATION)
  }

  @Test
  fun testKeysWhatsNewPreference() {
    assertEquals("com.moonlitdoor.amessage.whats.new.preference.button", Constants.Keys.WHATS_NEW_PREFERENCE)
  }

  @Test
  fun testKeysExperiments() {
    assertEquals("com.moonlitdoor.amessage.experiments.settings", Constants.Keys.EXPERIMENTS_SETTINGS)
  }

  @Test
  fun testKeysExperimentsVisible() {
    assertEquals("com.moonlitdoor.amessage.experiments.settings.visible", Constants.Keys.EXPERIMENTS_SETTINGS_VISIBLE)
  }

  @Test
  fun testKeysEmployeeSettings() {
    assertEquals("com.moonlitdoor.amessage.employee.settings", Constants.Keys.EMPLOYEE_SETTINGS)
  }

  @Test
  fun testKeysEmployeeSettingsVisible() {
    assertEquals("com.moonlitdoor.amessage.employee.settings.visible", Constants.Keys.EMPLOYEE_SETTINGS_VISIBLE)
  }

  @Test
  fun testKeysDeveloperSettings() {
    assertEquals("com.moonlitdoor.amessage.developer.settings", Constants.Keys.DEVELOPER_SETTINGS)
  }

  @Test
  fun testKeysDeveloperSettingsVisible() {
    assertEquals("com.moonlitdoor.amessage.developer.settings.visible", Constants.Keys.DEVELOPER_SETTINGS_VISIBLE)
  }

  @Test
  fun testValuesTheme() {
    assertNotNull(Constants.Values)
    assertEquals("1", Constants.Values.Theme.DARK)
    assertEquals("2", Constants.Values.Theme.MONSTER)
    assertEquals("3", Constants.Values.Theme.DEEP)
    assertEquals("4", Constants.Values.Theme.COOL)
  }

}