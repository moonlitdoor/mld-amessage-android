package com.moonlitdoor.amessage.domain.model

data class FrequentlyAskedQuestion(
  val id: Long,
  val rank: Long,
  val question: String,
  val answer: String,
  val visible: Boolean
)
