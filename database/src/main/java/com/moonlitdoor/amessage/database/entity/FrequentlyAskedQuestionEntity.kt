package com.moonlitdoor.amessage.database.entity

data class FrequentlyAskedQuestionEntity(
  val id: Long = 0,
  val rank: Long = 0,
  val question: String = "",
  val answer: String = "",
  val visible: Boolean = true
)