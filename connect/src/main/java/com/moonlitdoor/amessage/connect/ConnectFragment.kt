package com.moonlitdoor.amessage.connect

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.moonlitdoor.amessage.connect.databinding.FragmentConnectBinding

class ConnectFragment : androidx.fragment.app.Fragment() {

  private lateinit var binding: FragmentConnectBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    DataBindingUtil.inflate<FragmentConnectBinding>(inflater, R.layout.fragment_connect, container, false).also {
      it.toolbar.setNavigationOnClickListener { findNavController(this).navigateUp() }
      binding = it
      activity?.let { act ->
        if (act.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
          setup(true)
        } else {
          requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION)
        }
      }
    }.root

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    setup(requestCode == CAMERA_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED)
  }

  private fun setup(permissionGranted: Boolean) {
    binding.tabLayout.visibility = View.VISIBLE
    binding.viewPager2.adapter = object : FragmentStateAdapter(this) {

      override fun getItemCount() = if (permissionGranted) 3 else 2

      override fun createFragment(position: Int): Fragment = when (position) {
        0 -> PendingFragment()
        1 -> QrFragment()
        else -> ScanFragment()
      }

    }
    TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
      tab.setText(
        when (position) {
          0 -> PendingFragment.titleId
          1 -> QrFragment.titleId
          else -> ScanFragment.titleId
        }
      )
    }.attach()
  }

  companion object {
    private const val CAMERA_PERMISSION = 6235
  }

}
