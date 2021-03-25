package com.moonlitdoor.amessage.handle

//import android.app.AlertDialog
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.inputmethod.InputMethodManager
//import com.moonlitdoor.amessage.extensions.afterTextChanged
//import com.moonlitdoor.amessage.extensions.ignore
//import com.moonlitdoor.amessage.handle.databinding.DialogHandleCreateBinding
//
//class HandleCreateDialog private constructor(activity: androidx.fragment.app.FragmentActivity, viewModel: HandleViewModel) {
//
//  private val binding: DialogHandleCreateBinding by lazy {
//    DialogHandleCreateBinding.inflate(LayoutInflater.from(activity), null, false).also {
//      it.input.afterTextChanged { dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !it.isNullOrBlank() }
//    }
//  }
//  private val dialog: AlertDialog by lazy {
//    AlertDialog.Builder(activity)
//      .setCancelable(false)
//      .setTitle(R.string.title_handle)
//      .setView(binding.root)
//      .setPositiveButton(R.string.generic_ok) { _, _ ->
//        binding.handle?.let {
//          viewModel.setHandle(it)
//        }
//      }
//      .create()
//  }
//
//  init {
//    dialog.apply {
//      show()
//      getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
//    }
//    (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
//  }
//
//  companion object {
//    fun show(activity: androidx.fragment.app.FragmentActivity?, viewModel: HandleViewModel): Unit = activity?.let { HandleCreateDialog(it, viewModel) }.ignore()
//  }
//}
