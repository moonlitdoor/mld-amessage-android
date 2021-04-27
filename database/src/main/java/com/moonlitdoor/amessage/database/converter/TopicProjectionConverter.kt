package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.TopicProjection

class TopicProjectionConverter {

  @TypeConverter
  fun to(item: TopicProjection?): String? = item?.value

  @TypeConverter
  fun to(item: String?): TopicProjection? = item?.let { TopicProjection(it) }
}
