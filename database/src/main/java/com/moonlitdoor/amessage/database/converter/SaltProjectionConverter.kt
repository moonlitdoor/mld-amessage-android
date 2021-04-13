package com.moonlitdoor.amessage.database.converter

import androidx.room.TypeConverter
import com.moonlitdoor.amessage.database.projection.SaltProjection
import java.util.*

class SaltProjectionConverter {

  @TypeConverter
  fun to(item: SaltProjection): UUID = item.value


  @TypeConverter
  fun to(item: UUID): SaltProjection = SaltProjection(item)

}
