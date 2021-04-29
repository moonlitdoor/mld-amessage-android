package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.TitleProjection
import com.moonlitdoor.amessage.database.projection.TopicProjection
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.dto.ConversationCreatePayload

object ConversationMapper {
  fun map(payload: ConversationCreatePayload) = Conversation(
    conversationId = payload.conversationId,
    title = payload.title,
    topic = payload.topic,
    created = payload.created,
    keys = KeysMapper.mapToProjection(payload.keys),
    associatedData = AssociatedDataMapper.mapToProjection(payload.associatedData)
  )

  fun mapToEntity(conversation: Conversation) = ConversationEntity(
    conversationId = IdProjection(conversation.conversationId),
    title = conversation.title?.let { TitleProjection(it) },
    topic = conversation.topic?.let { TopicProjection(it) },
    keys = conversation.keys,
    associatedData = conversation.associatedData,
    created = conversation.created
  )
}
