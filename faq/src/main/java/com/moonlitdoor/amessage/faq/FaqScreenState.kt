package com.moonlitdoor.amessage.faq

import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion

sealed class FaqScreenState {

  object Loading : FaqScreenState()
  object Empty : FaqScreenState()
  data class Result(val items: List<FrequentlyAskedQuestion>) : FaqScreenState()
}
