package com.moonlitdoor.amessage.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.moonlitdoor.amessage.connect.databinding.FragmentPendingBinding
import com.moonlitdoor.amessage.connect.databinding.ListItemConnectionPendingBinding
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.extensions.ignore
import javax.inject.Inject

class PendingFragment : Fragment() {

  @Inject
  lateinit var viewModel: ConnectViewModel

  private val itemClickHandler: (Connection) -> Unit = { connection ->
    AlertDialog.Builder(requireContext())
      .setTitle(getString(R.string.connect, connection.handle))
      .setPositiveButton(R.string.confirm) { _, _ ->
        viewModel.confirm(connection)
      }
      .setNegativeButton(R.string.reject) { _, _ ->
        viewModel.reject(connection)
      }
      .show()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ConnectDI.get(requireActivity()).inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = FragmentPendingBinding.inflate(inflater, container, false).also {
    it.recyclerView.adapter = Adapter(itemClickHandler, LayoutInflater.from(activity))
    it.lifecycleOwner = this
    it.viewModel = viewModel
  }.root

  private class Adapter(private val itemClickHandler: (Connection) -> Unit, private val layoutInflater: LayoutInflater) :
    ListAdapter<Connection, ConnectionViewHolder>(object : DiffUtil.ItemCallback<Connection>() {
      override fun areItemsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConnectionViewHolder(ListItemConnectionPendingBinding.inflate(layoutInflater, parent, false))
    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) = holder.bind(getItem(position), itemClickHandler).ignore()
  }

  private class ConnectionViewHolder(private val binding: ListItemConnectionPendingBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
    fun bind(connection: Connection, itemClickHandler: (Connection) -> Unit) = binding.also {
      it.root.setOnClickListener { itemClickHandler.invoke(connection) }
      it.connection = connection
    }
  }

  companion object {
    val titleId = R.string.connect_list_title
  }

}
