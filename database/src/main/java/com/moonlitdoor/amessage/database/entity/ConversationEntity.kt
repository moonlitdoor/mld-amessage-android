package com.moonlitdoor.amessage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.KeysProjection
import com.moonlitdoor.amessage.database.projection.TitleProjection
import com.moonlitdoor.amessage.database.projection.TopicProjection

@Entity(tableName = "conversation")
data class ConversationEntity(
  @PrimaryKey
  @ColumnInfo(name = "conversation_id")
  val conversationId: IdProjection = IdProjection(),
  val title: TitleProjection?,
  val topic: TopicProjection?,
  val keys: KeysProjection = KeysProjection(),
  val associatedData: AssociatedDataProjection = AssociatedDataProjection(),
)
