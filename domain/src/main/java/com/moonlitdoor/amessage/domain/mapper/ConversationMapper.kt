package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.database.projection.TitleProjection
import com.moonlitdoor.amessage.database.projection.TopicProjection
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.dto.ConversationCreatePayload

object ConversationMapper {
  fun map(payload: ConversationCreatePayload) = Conversation(
    conversationId = Id(payload.conversationId),
    title = payload.title,
    topic = payload.topic,
    created = payload.created,
    keys = KeysMapper.map(payload.keys),
    associatedData = AssociatedDataMapper.map(payload.associatedData)
  )

  fun map(payload: ConversationEntity) = Conversation(
    conversationId = IdMapper.map(payload.conversationId),
    title = payload.title?.value,
    topic = payload.topic?.value,
    created = payload.created,
    keys = KeysMapper.map(payload.keys),
    associatedData = AssociatedDataMapper.map(payload.associatedData)
  )

  fun mapToEntity(conversation: Conversation) = ConversationEntity(
    conversationId = IdMapper.map(conversation.conversationId),
    title = conversation.title?.let { TitleProjection(it) },
    topic = conversation.topic?.let { TopicProjection(it) },
    keys = KeysMapper.mapToProjection(conversation.keys),
    associatedData = AssociatedDataMapper.mapToProjection(conversation.associatedData),
    created = conversation.created
  )
}
