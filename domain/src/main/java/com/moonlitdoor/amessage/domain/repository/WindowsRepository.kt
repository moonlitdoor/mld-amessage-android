package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.WindowsDao
import javax.inject.Inject

class WindowsRepository @Inject constructor(windowsDao: WindowsDao) {

  val windowsCount = windowsDao.count

}