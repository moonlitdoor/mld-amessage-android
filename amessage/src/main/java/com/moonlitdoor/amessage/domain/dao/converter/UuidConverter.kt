package com.moonlitdoor.amessage.domain.dao.converter

import androidx.room.TypeConverter
import java.util.*

class UuidConverter {

  @TypeConverter
  fun to(uuid: UUID?): String? = uuid?.toString()


  @TypeConverter
  fun to(uuid: String?): UUID? = uuid?.let { UUID.fromString(it) }

}
