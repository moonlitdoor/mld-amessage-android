package com.moonlitdoor.amessage.experiments.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.experiments.Experiment
import com.moonlitdoor.amessage.experiments.Experiments

class ExperimentsViewModel : ViewModel() {

  val experiments: LiveData<List<Experiment<*>>> = MutableLiveData<List<Experiment<*>>>().also { it.postValue(Experiments.experiments) }

}