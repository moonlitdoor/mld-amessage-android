package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.format.DateTimeFormatter

class InstantConverter {

  @TypeConverter
  fun to(item: Instant?): String? = item?.let { FORMATTER.format(it) }

  @TypeConverter
  fun to(item: String?): Instant? = item?.let { Instant.from(FORMATTER.parse(it)) }

  companion object {
    private val FORMATTER = DateTimeFormatter.ISO_INSTANT
  }
}
