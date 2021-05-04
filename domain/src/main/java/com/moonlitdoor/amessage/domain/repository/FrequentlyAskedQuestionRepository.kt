package com.moonlitdoor.amessage.domain.repository

import com.moonlitdoor.amessage.database.dao.FrequentlyAskedQuestionDao
import com.moonlitdoor.amessage.domain.mapper.FrequentlyAskedQuestionMapper
import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FrequentlyAskedQuestionRepository @Inject constructor(private val frequentlyAskedQuestionDao: FrequentlyAskedQuestionDao) {

  fun getFrequentlyAskedQuestions(): Flow<List<FrequentlyAskedQuestion>> = frequentlyAskedQuestionDao.getFrequentlyAskedQuestions().map {
    FrequentlyAskedQuestionMapper.map(it)
  }
}
