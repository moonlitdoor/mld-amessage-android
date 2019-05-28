package com.moonlitdoor.amessage.domain.repository

import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.domain.dao.WindowsDao

class WindowsRepository(windowsDao: WindowsDao) {

  val windowsCount: LiveData<Int> = windowsDao.count

}