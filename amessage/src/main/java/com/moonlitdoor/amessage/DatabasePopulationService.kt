package com.moonlitdoor.amessage

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.moonlitdoor.amessage.database.dao.KeyValueDao
import dagger.hilt.android.AndroidEntryPoint
//import com.moonlitdoor.amessage.extensions.ignore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DatabasePopulationService : IntentService("DatabasePopulationService") {

  @Inject
  lateinit var keyValueDao: KeyValueDao

  override fun onHandleIntent(intent: Intent?) {
    val handler = CoroutineExceptionHandler { _, exception ->
      println("CoroutineExceptionHandler got $exception")
    }

    GlobalScope.launch(handler) {
      keyValueDao.getId() ?: keyValueDao.insertId(UUID.randomUUID())
      keyValueDao.getPassword() ?: keyValueDao.insertPassword(UUID.randomUUID())
      keyValueDao.getSalt() ?: keyValueDao.insertSalt(UUID.randomUUID())
    }/*TODO.ignore()*/
  }

  companion object {
    @JvmStatic
    fun start(context: Context) {
      context.startService(Intent(context, DatabasePopulationService::class.java))
    }
  }
}