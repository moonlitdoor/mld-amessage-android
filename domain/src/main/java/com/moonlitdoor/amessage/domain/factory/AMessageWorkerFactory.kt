package com.moonlitdoor.amessage.domain.factory

//import android.content.Context
//import androidx.work.ListenableWorker
//import androidx.work.WorkerFactory
//import androidx.work.WorkerParameters
//import com.moonlitdoor.amessage.database.dao.ConnectionDao
//import com.moonlitdoor.amessage.database.dao.ProfileDao
//import com.moonlitdoor.amessage.domain.work.ConnectionConfirmationWorker
//import com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker
//import com.moonlitdoor.amessage.network.NetworkClient
//import javax.inject.Inject
//
//class AMessageWorkerFactory @Inject constructor(private val profileDao: ProfileDao, private val connectionDao: ConnectionDao, private val client: NetworkClient) : WorkerFactory() {
//
//  override fun createWorker(context: Context, workerClassName: String, parameters: WorkerParameters): ListenableWorker? =
//    when (workerClassName) {
//      ConnectionConfirmationWorker::class.java.name -> ConnectionConfirmationWorker(
//        connectionDao = connectionDao,
//        client = client,
//        context = context,
//        parameters = parameters
//      )
//      ConnectionInviteWorker::class.java.name -> ConnectionInviteWorker(
//        profileDao = profileDao,
//        connectionDao = connectionDao,
//        client = client,
//        context = context,
//        parameters = parameters
//      )
//      else -> null
//    }
//}
