package com.moonlitdoor.amessage.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.connect.databinding.FragmentPendingBinding
import com.moonlitdoor.amessage.connect.databinding.ListItemConnectionPendingInvitedBinding
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.extensions.ignore
import javax.inject.Inject

class PendingFragment : Fragment() {

  @Inject
  lateinit var viewModel: ConnectViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ConnectDI.get().inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = FragmentPendingBinding.inflate(inflater, container, false).also {
    it.lifecycleOwner = this
//    it.viewModel = viewModel.also { vm ->
//      vm.selectedConnection.observe(this, Observer { conn ->
//        conn?.let { c ->
//          if (c.state == Connection.State.Invited) {
//            AlertDialog.Builder(activity)
//              .setTitle(getString(R.string.connect, c.handle))
//              .setPositiveButton(R.string.confirm) { _, _ ->
//                vm.confirmConnection(c)
//              }
//              .setNegativeButton(R.string.reject) { _, _ ->
//                vm.rejectConnection(c)
//              }
//              .show()
//          }
//        }
//      })
//    }
    it.recyclerView.adapter = Adapter(viewModel, LayoutInflater.from(activity))
  }.root

  private class Adapter(private val viewModel: ConnectViewModel, private val layoutInflater: LayoutInflater) :
    ListAdapter<Connection, ConnectionViewHolder>(object : DiffUtil.ItemCallback<Connection>() {
      override fun areItemsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConnectionViewHolder(ListItemConnectionPendingInvitedBinding.inflate(layoutInflater, parent, false))
    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) = holder.bind(getItem(position), viewModel).ignore()
  }

  private class ConnectionViewHolder(private val binding: ListItemConnectionPendingInvitedBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(connection: Connection, viewModel: ConnectViewModel) = binding.also {
      it.connection = connection
      it.viewModel = viewModel
    }
  }

  companion object {
    val titleId = R.string.connect_list_title
  }

}
