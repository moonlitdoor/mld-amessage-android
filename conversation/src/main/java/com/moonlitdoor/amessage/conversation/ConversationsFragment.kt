package com.moonlitdoor.amessage.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.components.WhatsNewBottomSheetDialog
import com.moonlitdoor.amessage.components.databinding.NavigationHeaderBinding
import com.moonlitdoor.amessage.conversation.databinding.FragmentConversationsBinding
import com.moonlitdoor.amessage.conversation.databinding.ListItemConversationBinding
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.experiments.helper.NavigationMenuExperimentHelper
import com.moonlitdoor.amessage.extensions.ignore
import com.moonlitdoor.amessage.windows.WindowsCountObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConversationsFragment : androidx.fragment.app.Fragment() {

  private val viewModel: ConversationViewModel by viewModel()
  private val adapter by lazy { Adapter(viewModel, LayoutInflater.from(activity)) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    FragmentConversationsBinding.inflate(inflater, container, false).also {
      it.lifecycleOwner = this
      it.viewModel = viewModel
      findNavController().also { navController ->
        it.toolbar.setupWithNavController(navController, AppBarConfiguration(setOf(R.id.connections_fragment, R.id.conversations_fragment), it.drawerLayout))
        it.navigationView.setupWithNavController(navController)
      }
      WhatsNewBottomSheetDialog.setMenuItemListener(activity, it.drawerLayout, it.navigationView.menu.findItem(R.id.navigation_whats_new))
      it.navigationView.addHeaderView(NavigationHeaderBinding.inflate(inflater, null, false).also { header ->
        header.lifecycleOwner = this
        header.handle = viewModel.handle
      }.root)
      WindowsCountObserver(this, viewModel.windowsCount, it.navigationView.menu.findItem(R.id.windows_fragment))
      it.recyclerView.adapter = adapter
      it.fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.actionConversationsFragmentToConversationsCreateFragment))
      WhatsNewBottomSheetDialog.show(activity)
      NavigationMenuExperimentHelper.help(it.navigationView.menu)
    }.root

  private class Adapter(private val viewModel: ConversationViewModel, private val layoutInflater: LayoutInflater) : ListAdapter<Conversation, ConversationViewHolder>(object : DiffUtil.ItemCallback<Conversation>() {
    override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConversationViewHolder(ListItemConversationBinding.inflate(layoutInflater, parent, false))
    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) = holder.bind(getItem(position), viewModel).ignore()
  }

  private class ConversationViewHolder(private val binding: ListItemConversationBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(conversation: Conversation, viewModel: ConversationViewModel) = binding.also {
      it.conversation = conversation
      it.viewModel = viewModel
    }
  }
}
