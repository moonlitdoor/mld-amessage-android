package com.moonlitdoor.amessage.connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.ui.core.Text
import com.moonlitdoor.amessage.components.WhatsNewBottomSheetDialog
import com.moonlitdoor.amessage.connection.databinding.FragmentConnectionsBinding
import com.moonlitdoor.amessage.connection.databinding.ListItemConnectionConnectedBinding
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.experiments.Experiment
import com.moonlitdoor.amessage.experiments.Experiments
import com.moonlitdoor.amessage.experiments.helper.NavigationMenuExperimentHelper
import com.moonlitdoor.amessage.extensions.ignore
import com.moonlitdoor.amessage.extensions.setComposable
import com.moonlitdoor.amessage.windows.WindowsCountObserver
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.moonlitdoor.amessage.ids.R as N

class ConnectionsFragment : androidx.fragment.app.Fragment(), Observer<String?> {

  private val viewModel: ConnectionViewModel by viewModel()
  private val handleViewModel: com.moonlitdoor.amessage.handle.HandleViewModel by viewModel()
  private val adapter by lazy { Adapter(viewModel, LayoutInflater.from(activity)) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    when (Experiments.USE_COMPOSE.value) {
      Experiment.BOOLEAN.TRUE -> setComposable {
        Text("Compose With Extension II")
      }
      Experiment.BOOLEAN.FALSE -> FragmentConnectionsBinding.inflate(inflater, container, false).also {
        it.lifecycleOwner = this
        it.viewModel = viewModel
        findNavController().also { navController ->
          it.toolbar.setupWithNavController(navController, AppBarConfiguration(setOf(R.id.connections_fragment, R.id.conversations_fragment), it.drawerLayout))
          it.navigationView.setupWithNavController(navController)
        }
        WhatsNewBottomSheetDialog.setMenuItemListener(activity, it.drawerLayout, it.navigationView.menu.findItem(R.id.navigation_whats_new))
        it.fab.setOnClickListener(Navigation.createNavigateOnClickListener(N.id.action_connections_fragment_to_connect_fragment))
//        it.navigationView.addHeaderView(NavigationHeaderBinding.inflate(inflater, null, false).also { header ->
//          header.lifecycleOwner = this
//          header.handle = viewModel.handle.also { h -> h.observe(header.life, this) }
//        }.root)
        it.recyclerView.adapter = adapter
        WindowsCountObserver(this, viewModel.windowsCount, it.navigationView.menu.findItem(R.id.windows_fragment))
        NavigationMenuExperimentHelper.help(it.navigationView.menu)
      }.root

    }

  override fun onChanged(handle: String?): Unit = handle?.run { WhatsNewBottomSheetDialog.show(activity) } ?: com.moonlitdoor.amessage.handle.HandleCreateDialog.show(activity, handleViewModel)

  private class Adapter(private val viewModel: ConnectionViewModel, private val layoutInflater: LayoutInflater) : ListAdapter<Connection, ConnectionViewHolder>(object : DiffUtil.ItemCallback<Connection>() {
    override fun areItemsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConnectionViewHolder(ListItemConnectionConnectedBinding.inflate(layoutInflater, parent, false))
    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) = holder.bind(getItem(position), viewModel).ignore()
  }

  private class ConnectionViewHolder(private val binding: ListItemConnectionConnectedBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(connection: Connection, viewModel: ConnectionViewModel) = binding.also {
      it.connection = connection
      it.viewModel = viewModel
    }
  }
}
