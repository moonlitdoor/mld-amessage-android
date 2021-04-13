package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.TokenProjection

class TokenProjectionConverter {

  @TypeConverter
  fun to(item: TokenProjection): String = item.value


  @TypeConverter
  fun to(item: String?): TokenProjection = TokenProjection(item ?: "")

}
