package com.moonlitdoor.amessage.connect

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.connect.databinding.FragmentPendingBinding
import com.moonlitdoor.amessage.connect.databinding.ListItemConnectionPendingInvitedBinding
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.extensions.ignore
import org.koin.androidx.viewmodel.ext.android.viewModel

class PendingFragment : TitledFragmentPagerAdapter.TitledFragment() {

  override fun getTitleId() = R.string.connect_list_title

  private val viewModel1: ConnectViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = DataBindingUtil.inflate<FragmentPendingBinding>(inflater, R.layout.fragment_pending, container, false).also {
    it.lifecycleOwner = activity
    it.viewModel = viewModel1.also { vm ->
      vm.selectedConnection.observe(this, Observer { conn ->
        conn?.let { c ->
          if (c.state == Connection.State.Invited) {
            AlertDialog.Builder(activity)
              .setTitle(getString(R.string.connect, c.handle))
              .setPositiveButton(R.string.confirm) { _, _ ->
                vm.confirmConnection(c)
              }
              .setNegativeButton(R.string.reject) { _, _ ->
                vm.rejectConnection(c)
              }
              .show()
          }
        }
      })
    }
    it.recyclerView.adapter = Adapter(viewModel1, LayoutInflater.from(activity))
  }.root

  private class Adapter(private val viewModel: ConnectViewModel, private val layoutInflater: LayoutInflater) : ListAdapter<Connection, ConnectionViewHolder>(object : DiffUtil.ItemCallback<Connection>() {
    override fun areItemsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem == newItem
  }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConnectionViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.list_item_connection_pending_invited, parent, false))
    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) = holder.bind(getItem(position), viewModel).ignore()
  }

  private class ConnectionViewHolder(private val binding: ListItemConnectionPendingInvitedBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(connection: Connection, viewModel: ConnectViewModel) = binding.also {
      it.connection = connection
      it.viewModel = viewModel
    }
  }
}