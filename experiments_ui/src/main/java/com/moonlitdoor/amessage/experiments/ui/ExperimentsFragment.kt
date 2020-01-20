package com.moonlitdoor.amessage.experiments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moonlitdoor.amessage.experiments.Experiment
import com.moonlitdoor.amessage.experiments.ui.databinding.FragmentExperimentsBinding
import com.moonlitdoor.amessage.experiments.ui.databinding.ListItemExperimentBinding
import com.moonlitdoor.amessage.extensions.ignore
import javax.inject.Inject

class ExperimentsFragment : Fragment() {

  @Inject
  lateinit var viewModel: ExperimentsViewModel
  private val adapter by lazy { Adapter(LayoutInflater.from(activity), this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    ExperimentsUiDI.get().inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//    when (Experiments.USE_COMPOSE_EXPERIMENTS.value) {
//      Experiment.BOOLEAN.TRUE -> setComposable {
//        ExperimentsList(Experiments.experiments)
//      }
//      Experiment.BOOLEAN.FALSE ->
    FragmentExperimentsBinding.inflate(inflater, container, false).also {
      it.toolbar.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
      it.viewModel = viewModel
      it.lifecycleOwner = this
      it.recyclerView.adapter = adapter
    }.root
//    }

  private class Adapter(private val layoutInflater: LayoutInflater, private val lifecycleOwner: LifecycleOwner) : ListAdapter<Experiment<*>, ExperimentViewHolder>(object : DiffUtil.ItemCallback<Experiment<*>>() {
    override fun areItemsTheSame(oldItem: Experiment<*>, newItem: Experiment<*>): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Experiment<*>, newItem: Experiment<*>): Boolean = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ExperimentViewHolder = ExperimentViewHolder(ListItemExperimentBinding.inflate(layoutInflater, parent, false), lifecycleOwner)
    override fun onBindViewHolder(holder: ExperimentViewHolder, position: Int) = holder.bind(getItem(position)).ignore()
  }


  private class ExperimentViewHolder(private val binding: ListItemExperimentBinding, private val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.root), AdapterViewBindingAdapter.OnItemSelected {

    fun bind(experiment: Experiment<*>) = binding.also {
      it.experiment = experiment
      it.handler = this
      it.lifecycleOwner = lifecycleOwner
    }.ignore()

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
      binding.experiment?.let {
        it.localValue = it.options[position] as String
      }.ignore()


  }


}