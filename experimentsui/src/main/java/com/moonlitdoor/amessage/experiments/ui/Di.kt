package com.moonlitdoor.amessage.experiments.ui

import com.moonlitdoor.amessage.experiments.experimentsDi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val experimentsUiDi = experimentsDi + listOf(module {
  viewModel { ExperimentsViewModel() }
})