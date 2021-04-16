package com.moonlitdoor.amessage.dto.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.Instant
import java.time.format.DateTimeFormatter

object InstantAdapter : TypeAdapter<Instant>() {

  private val FORMATTER = DateTimeFormatter.ISO_INSTANT

  override fun write(writer: JsonWriter, value: Instant) {
    writer.value(FORMATTER.format(value))
  }

  override fun read(reader: JsonReader): Instant = Instant.from(FORMATTER.parse(reader.nextString()))

}