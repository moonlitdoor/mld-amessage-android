package com.moonlitdoor.amessage.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moonlitdoor.amessage.components.ItemDecorationDivider
import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion
import com.moonlitdoor.amessage.extensions.ignore
import com.moonlitdoor.amessage.help.databinding.FragmentHelpBinding
import com.moonlitdoor.amessage.help.databinding.ListItemFrequentlyAskedQuestionBinding
import com.moonlitdoor.amessage.resources.R
import javax.inject.Inject

class HelpFragment : androidx.fragment.app.Fragment() {

  @Inject
  lateinit var viewModel: HelpViewModel
  private val adapter by lazy { Adapter(LayoutInflater.from(activity), viewModel, this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    HelpDI.init().inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentHelpBinding.inflate(inflater, container, false).also {

      it.lifecycleOwner = this
      it.viewModel = viewModel
      it.recyclerView.addItemDecoration(ItemDecorationDivider(requireContext()))
      it.recyclerView.adapter = adapter
      it.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
      it.toolbar.setNavigationOnClickListener { findNavController(this).navigateUp() }
    }.root

  private class Adapter(private val layoutInflater: LayoutInflater, private val viewModel: HelpViewModel, private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<FrequentlyAskedQuestion, FrequentlyAskedQuestionViewHolder>(object : DiffUtil.ItemCallback<FrequentlyAskedQuestion>() {
      override fun areItemsTheSame(oldItem: FrequentlyAskedQuestion, newItem: FrequentlyAskedQuestion): Boolean = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: FrequentlyAskedQuestion, newItem: FrequentlyAskedQuestion): Boolean = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FrequentlyAskedQuestionViewHolder(ListItemFrequentlyAskedQuestionBinding.inflate(layoutInflater, parent, false), viewModel, lifecycleOwner)
    override fun onBindViewHolder(holder: FrequentlyAskedQuestionViewHolder, position: Int) = holder.bind(getItem(position)).ignore()
  }

  private class FrequentlyAskedQuestionViewHolder(private val binding: ListItemFrequentlyAskedQuestionBinding, private val viewModel: HelpViewModel, private val lifecycleOwner: LifecycleOwner) : RecyclerView.ViewHolder(binding.root) {
    fun bind(frequentlyAskedQuestion: FrequentlyAskedQuestion) = binding.also {
      it.viewModel = viewModel
      it.lifecycleOwner = lifecycleOwner
      it.frequentlyAskedQuestion = frequentlyAskedQuestion
    }
  }
}
