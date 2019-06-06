package com.moonlitdoor.amessage.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.components.WhatsNewBottomSheetDialog
import com.moonlitdoor.amessage.connection.databinding.NavigationHeaderBinding
import com.moonlitdoor.amessage.databinding.FragmentConversationsBinding
import com.moonlitdoor.amessage.databinding.ListItemConversationBinding
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.extensions.ignore
import com.moonlitdoor.amessage.windows.WindowsCountObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConversationsFragment : androidx.fragment.app.Fragment() {

  private val viewModel: ConversationViewModel by viewModel()
  private val adapter by lazy { Adapter(viewModel, LayoutInflater.from(activity)) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentConversationsBinding>(inflater, R.layout.fragment_conversations, container, false).also {
      it.setLifecycleOwner(this)
      it.viewModel = viewModel
      setupWithNavController(it.toolbar, findNavController(this), it.drawerLayout)
      setupWithNavController(it.navigationView, findNavController(this))
      WhatsNewBottomSheetDialog.setMenuItemListener(activity, it.drawerLayout, it.navigationView.menu.findItem(R.id.navigation_whats_new))
      it.navigationView.addHeaderView(DataBindingUtil.inflate<NavigationHeaderBinding>(inflater, R.layout.navigation_header, null, false).also {
        it.setLifecycleOwner(this)
        it.handle = viewModel.handle
      }.root)
      WindowsCountObserver(this, viewModel.windowsCount, it.navigationView.menu.findItem(R.id.windows_fragment))
      it.recyclerView.adapter = adapter
      it.fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.actionConversationsFragmentToConversationsCreateFragment))
      WhatsNewBottomSheetDialog.show(activity)
    }.root

  private class Adapter(private val viewModel: ConversationViewModel, private val layoutInflater: LayoutInflater) : ListAdapter<Conversation, ConversationViewHolder>(object : DiffUtil.ItemCallback<Conversation>() {
    override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConversationViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.list_item_conversation, parent, false))
    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) = holder.bind(getItem(position), viewModel).ignore()
  }

  private class ConversationViewHolder(private val binding: ListItemConversationBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(conversation: Conversation, viewModel: ConversationViewModel) = binding.also {
      it.conversation = conversation
      it.viewModel = viewModel
    }
  }
}
