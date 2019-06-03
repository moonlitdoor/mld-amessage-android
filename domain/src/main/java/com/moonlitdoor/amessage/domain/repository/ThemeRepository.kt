package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.ThemeDao

class ThemeRepository(dao: ThemeDao) {

  val theme = dao.theme

}