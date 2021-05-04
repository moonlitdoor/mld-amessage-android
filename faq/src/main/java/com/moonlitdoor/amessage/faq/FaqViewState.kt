package com.moonlitdoor.amessage.faq

import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion

sealed class FaqViewState {

  object Loading : FaqViewState()
  object Empty : FaqViewState()
  data class Result(val items: List<FrequentlyAskedQuestion>) : FaqViewState()
}
