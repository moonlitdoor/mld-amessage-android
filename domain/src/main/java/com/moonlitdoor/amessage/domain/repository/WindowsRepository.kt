package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.WindowsDao

class WindowsRepository(windowsDao: WindowsDao) {

  val windowsCount = windowsDao.count

}