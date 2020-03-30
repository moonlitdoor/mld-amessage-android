package com.moonlitdoor.amessage.constants

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConstantsTest {

  @Before
  fun setup() {
    ConstantsDI.init(InstrumentationRegistry.getInstrumentation().targetContext)
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
  fun testSharedPreferencesVersionCode() {
    assertEquals("com.moonlitdoor.amessage.version_code", Constants.SharedPreferences.VERSION_CODE)
  }

  @Test
  fun testSharedPreferencesHandle() {
    assertEquals("com.moonlitdoor.amessage.domain.dao.ProfileDao.HANDLE", Constants.SharedPreferences.HANDLE)
  }

  @Test
  fun testSharedPreferencesToken() {
    assertEquals("com.moonlitdoor.amessage.domain.dao.ProfileDao.TOKEN", Constants.SharedPreferences.TOKEN)
  }

  @Test
  fun testSharedPreferencesID() {
    assertEquals("com.moonlitdoor.amessage.domain.dao.ProfileDao.ID", Constants.SharedPreferences.ID)
  }

  @Test
  fun testSharedPreferencesPassword() {
    assertEquals("com.moonlitdoor.amessage.domain.dao.ProfileDao.PASSWORD", Constants.SharedPreferences.PASSWORD)
  }

  @Test
  fun testSharedPreferencesSalt() {
    assertEquals("com.moonlitdoor.amessage.domain.dao.ProfileDao.SALT", Constants.SharedPreferences.SALT)
  }

  @Test
  fun testKeysScreens() {
    assertEquals("com.moonlitdoor.amessage.screens.count", Constants.Keys.SCREENS)
    assertEquals(5, Constants.Keys.Defaults.SCREENS)
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

}