package com.moonlitdoor.amessage.connect

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import com.moonlitdoor.amessage.connect.databinding.FragmentInvitedBinding
//import com.moonlitdoor.amessage.connect.databinding.ListItemConnectionInvitedBinding
//import com.moonlitdoor.amessage.domain.model.Connection
//import com.moonlitdoor.amessage.extensions.ignore
//import timber.log.Timber
//import javax.inject.Inject
//
//class InvitedFragment : Fragment() {
//
//  @Inject
//  lateinit var viewModel: ConnectViewModelOld
//
//  private val adapter by lazy { Adapter(viewModel, LayoutInflater.from(activity)) }
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    ConnectDI.get(requireActivity()).inject(this)
//  }
//
//  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
//    FragmentInvitedBinding.inflate(inflater, container, false).also {
//      it.recyclerView.adapter = adapter
//      it.lifecycleOwner = this
//      it.viewModel = viewModel
//    }.root
//
//  private class Adapter(private val viewModel: ConnectViewModelOld, private val layoutInflater: LayoutInflater) :
//    ListAdapter<Connection, ConnectionViewHolder>(object : DiffUtil.ItemCallback<Connection>() {
//      override fun areItemsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem.id == newItem.id
//      override fun areContentsTheSame(oldItem: Connection, newItem: Connection): Boolean = oldItem == newItem
//    }) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ConnectionViewHolder(ListItemConnectionInvitedBinding.inflate(layoutInflater, parent, false))
//    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) = holder.bind(getItem(position), viewModel)
//  }
//
//  private class ConnectionViewHolder(private val binding: ListItemConnectionInvitedBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
//    fun bind(connection: Connection, viewModel: ConnectViewModelOld) = binding.also {
//      Timber.i("BINDING NOW")
//
//      it.connection = connection
//      it.viewModel = viewModel
//    }.ignore()
//  }
//
//  companion object {
//    val titleId = R.string.invited_list_title
//  }
//
//}
