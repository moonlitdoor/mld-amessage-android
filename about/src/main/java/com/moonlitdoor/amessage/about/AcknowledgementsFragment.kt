package com.moonlitdoor.amessage.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.about.databinding.FragmentAcknowledgementsBinding
import com.moonlitdoor.amessage.about.databinding.ListItemAcknowledgementBinding
import com.moonlitdoor.amessage.components.ItemDecorationDivider
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.domain.model.Acknowledgement

class AcknowledgementsFragment : TitledFragmentPagerAdapter.TitledFragment() {

  override fun getTitleId() = R.string.about_page_view_acknowledgements

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentAcknowledgementsBinding>(inflater, R.layout.fragment_acknowledgements, container, false).also {
      it.recyclerView.addItemDecoration(ItemDecorationDivider(activity!!))
      it.recyclerView.adapter = Adapter().also {
        it.submitList(resources.getStringArray(R.array.about_acknowledgements_list).map { Acknowledgement.create(it) })
      }
    }.root

  private inner class Adapter : ListAdapter<Acknowledgement, AcknowledgementViewHolder>(object : DiffUtil.ItemCallback<Acknowledgement>() {
    override fun areItemsTheSame(oldItem: Acknowledgement, newItem: Acknowledgement) = oldItem.title == newItem.title
    override fun areContentsTheSame(oldItem: Acknowledgement, newItem: Acknowledgement) = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AcknowledgementViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.list_item_acknowledgement, parent, false))
    override fun onBindViewHolder(holder: AcknowledgementViewHolder, position: Int) = holder.bind(getItem(position))
  }

  private inner class AcknowledgementViewHolder(private val binding: ListItemAcknowledgementBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    fun bind(acknowledgement: Acknowledgement) {
      binding.acknowledgement = acknowledgement
    }

  }


}