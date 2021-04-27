package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.TitleProjection

class TitleProjectionConverter {

  @TypeConverter
  fun to(item: TitleProjection?): String? = item?.value

  @TypeConverter
  fun to(item: String?): TitleProjection? = item?.let { TitleProjection(it) }
}
