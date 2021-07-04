package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.KeyValueRemoteEntity
import com.moonlitdoor.amessage.domain.model.KeyValue

object KeyValueMapper {

  fun map(list: List<KeyValueRemoteEntity>): List<KeyValue> = list.map { map(it) }

  fun map(entity: KeyValueRemoteEntity): KeyValue = KeyValue(
    key = entity.key,
    value = entity.value
  )
}
