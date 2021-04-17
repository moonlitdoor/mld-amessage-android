package com.moonlitdoor.amessage.help

//class HelpViewModel @Inject constructor(private val frequentlyAskedQuestionRepository: FrequentlyAskedQuestionRepository) : ViewModel() {
//
//  @ExperimentalCoroutinesApi
//  val frequentlyAskedQuestions: LiveData<List<FrequentlyAskedQuestion>> =
//    frequentlyAskedQuestionRepository.getFrequentlyAskedQuestions().map { list -> list.sortedBy { it.rank }.filter { it.visible } }.asLiveData(viewModelScope.coroutineContext)
//
//  val expanded = MutableLiveData<Long>().apply {
//    value = Long.MAX_VALUE
//  }
//
//  fun expand(id: Long) {
//    expanded.value = when {
//      expanded.value != id -> id
//      else -> Long.MAX_VALUE
//    }
//  }
//}