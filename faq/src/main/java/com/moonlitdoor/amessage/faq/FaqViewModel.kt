package com.moonlitdoor.amessage.faq

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.FrequentlyAskedQuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FaqViewModel @Inject constructor(repository: FrequentlyAskedQuestionRepository) : ViewModel() {

  val screenState: Flow<FaqScreenState> = repository.getFrequentlyAskedQuestions().map {
    when (it.isNotEmpty()) {
      true -> FaqScreenState.Result(it)
      false -> FaqScreenState.Empty
    }
  }
}
