package com.moonlitdoor.amessage.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion
import com.moonlitdoor.amessage.domain.repository.FrequentlyAskedQuestionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HelpViewModel @Inject constructor(private val frequentlyAskedQuestionRepository: FrequentlyAskedQuestionRepository) : ViewModel() {

  @ExperimentalCoroutinesApi
  val frequentlyAskedQuestions: LiveData<List<FrequentlyAskedQuestion>> =
    frequentlyAskedQuestionRepository.getFrequentlyAskedQuestions().map { list -> list.sortedBy { it.rank }.filter { it.visible } }.asLiveData(viewModelScope.coroutineContext)

  val expanded = MutableLiveData<Long>().apply {
    value = Long.MAX_VALUE
  }

  fun expand(id: Long) {
    expanded.value = when {
      expanded.value != id -> id
      else -> Long.MAX_VALUE
    }
  }
}