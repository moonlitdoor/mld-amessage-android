package com.moonlitdoor.amessage.connections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.components.WhatsNewBottomSheetDialog
import com.moonlitdoor.amessage.components.WindowsCountObserver
import com.moonlitdoor.amessage.connections.databinding.FragmentConnectionsBinding
import com.moonlitdoor.amessage.connections.databinding.ListItemConnectionConnectedBinding
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.experiments.helper.NavigationMenuExperimentHelper
import com.moonlitdoor.amessage.extensions.ignore
import com.moonlitdoor.amessage.handle.HandleViewModel
import javax.inject.Inject
import com.moonlitdoor.amessage.ids.R as N

class ConnectionsFragment : androidx.fragment.app.Fragment(), Observer<HandleProjection?> {

  @Inject
  lateinit var viewModel: ConnectionsViewModel

  @Inject
  lateinit var handleViewModel: HandleViewModel
  private val adapter by lazy { Adapter(viewModel, LayoutInflater.from(activity)) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ConnectionsDI.get(requireActivity()).inject(this)
    viewModel.handle.observe(this, this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    if (false) {
      ComposeView(requireContext()).apply {
        setContent {
          MaterialTheme {
            Surface {
              Button(onClick = {}) {
                Text(text = "Connections")
              }
            }
          }
        }
      }
    } else {
      FragmentConnectionsBinding.inflate(inflater, container, false).also {
        findNavController().also { navController ->
          it.toolbar.setupWithNavController(navController, AppBarConfiguration(setOf(R.id.connections_fragment, R.id.conversations_fragment), it.drawerLayout))
          it.navigationView.setupWithNavController(navController)
        }
        WhatsNewBottomSheetDialog.setMenuItemListener(activity, it.drawerLayout, it.navigationView.menu.findItem(R.id.navigation_whats_new))
        it.fab.setOnClickListener(Navigation.createNavigateOnClickListener(N.id.action_connections_fragment_to_connect_fragment))
//        it.navigationView.addHeaderView(NavigationHeaderBinding.inflate(inflater, null, false).also { header ->
//          header.lifecycleOwner = this
//          header.handle = viewModel.handle.also { h -> h.observe(header.life, this) }
//        }.com.moonlitdoor.amessage.root)
        it.recyclerView.adapter = adapter
        WindowsCountObserver(this, viewModel.windowsCount, it.navigationView.menu.findItem(R.id.windows_fragment))
        NavigationMenuExperimentHelper.help(it.navigationView.menu)
        it.lifecycleOwner = this
        it.viewModel = viewModel
      }.root
    }

  override fun onChanged(handle: HandleProjection?): Unit =
    handle?.value?.run { WhatsNewBottomSheetDialog.show(activity) } ?: com.moonlitdoor.amessage.handle.HandleCreateDialog.show(activity, handleViewModel)

  private class Adapter(private val viewModel: ConnectionsViewModel, private val layoutInflater: LayoutInflater) :
    ListAdapter<Connection, ConnectionViewHolder>(object : DiffUtil.ItemCallback<Connection>() {
      override fun areItemsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConnectionViewHolder(ListItemConnectionConnectedBinding.inflate(layoutInflater, parent, false))
    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) = holder.bind(getItem(position), viewModel).ignore()
  }

  private class ConnectionViewHolder(private val binding: ListItemConnectionConnectedBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(connection: Connection, viewModel: ConnectionsViewModel) = binding.also {
      it.connection = connection
      it.viewModel = viewModel
    }
  }
}
