package com.moonlitdoor.amessage.conversations.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.conversations.ConversationsDI
import com.moonlitdoor.amessage.conversations.R
import com.moonlitdoor.amessage.conversations.databinding.FragmentConversationParticipantsBinding
import com.moonlitdoor.amessage.conversations.databinding.ListItemSelectableConnectionBinding
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.SelectableConnection
import com.moonlitdoor.amessage.extensions.ignore
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import javax.inject.Inject

class ConversationParticipantsFragment : androidx.fragment.app.Fragment(), Step {

  @Inject
  lateinit var viewModel: ConversationCreateViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ConversationsDI.get().inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentConversationParticipantsBinding.inflate(inflater, container, false).also {
      it.lifecycleOwner = this
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

  private class Adapter(private val layoutInflater: LayoutInflater) :
    ListAdapter<SelectableConnection, SelectableConnectionViewHolder>(object : DiffUtil.ItemCallback<SelectableConnection>() {
      override fun areItemsTheSame(oldItem: SelectableConnection, newItem: SelectableConnection): Boolean = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: SelectableConnection, newItem: SelectableConnection): Boolean = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SelectableConnectionViewHolder(ListItemSelectableConnectionBinding.inflate(layoutInflater, parent, false))
    override fun onBindViewHolder(holder: SelectableConnectionViewHolder, position: Int) = holder.bind(getItem(position)).ignore()
  }

  private class SelectableConnectionViewHolder(private val binding: ListItemSelectableConnectionBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(connection: SelectableConnection) = binding.also {
      it.connection = connection
    }
  }
}