package com.moonlitdoor.amessage.conversation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.conversation.R
import com.moonlitdoor.amessage.conversation.databinding.FragmentConversationParticipantsBinding
import com.moonlitdoor.amessage.conversation.databinding.ListItemSelectableConnectionBinding
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.SelectableConnection
import com.moonlitdoor.amessage.extensions.ignore
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConversationParticipantsFragment : androidx.fragment.app.Fragment(), Step {

  private val viewModel: ConversationCreateViewModel by sharedViewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentConversationParticipantsBinding>(inflater, R.layout.fragment_conversation_participants, container, false).also {
      it.setLifecycleOwner(this)
      it.viewModel = viewModel
      it.recyclerView.adapter = Adapter(LayoutInflater.from(activity))
    }.root

  override fun onSelected() {
  }

  override fun verifyStep(): VerificationError? = if (viewModel.connections.value?.let { it.count { it.selected } > 0 } == true) {
    viewModel.conversation?.let { conversation ->
      viewModel.connections.value?.let { connections ->
        conversation.participants = connections.filter { it.selected }.map { Connection.from(it) }
      }
    }
    null
  } else {
    VerificationError(getString(R.string.preference_theme))
  }


  override fun onError(error: VerificationError) {}

  private class Adapter(private val layoutInflater: LayoutInflater) : ListAdapter<SelectableConnection, SelectableConnectionViewHolder>(object : DiffUtil.ItemCallback<SelectableConnection>() {
    override fun areItemsTheSame(oldItem: SelectableConnection, newItem: SelectableConnection): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: SelectableConnection, newItem: SelectableConnection): Boolean = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SelectableConnectionViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.list_item_selectable_connection, parent, false))
    override fun onBindViewHolder(holder: SelectableConnectionViewHolder, position: Int) = holder.bind(getItem(position)).ignore()
  }

  private class SelectableConnectionViewHolder(private val binding: ListItemSelectableConnectionBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(connection: SelectableConnection) = binding.also {
      it.connection = connection
    }
  }
}