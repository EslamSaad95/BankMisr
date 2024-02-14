package com.app.bank_misr.data.util

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.fragment.app.Fragment

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */
@JvmInline
value class Permission(val result: ActivityResultLauncher<Array<String>>)

sealed class PermissionState {
  object Granted : PermissionState()
  object Denied : PermissionState()
  object PermanentlyDenied : PermissionState()
}

private fun getPermissionState(
  activity: Activity?,
  result: Map<String, Boolean>
): PermissionState {
  val deniedList: List<String> = result.filter {
    it.value.not()
  }.map {
    it.key
  }

  var state = when (deniedList.isEmpty()) {
    true -> PermissionState.Granted
    false -> PermissionState.Denied
  }

  if (state == PermissionState.Denied) {
    val permanentlyMappedList = deniedList.map {
      activity?.let { activity ->
        shouldShowRequestPermissionRationale(activity, it)
      }
    }

    if (permanentlyMappedList.contains(false)) {
      state = PermissionState.PermanentlyDenied
    }
  }
  return state
}

fun AppCompatActivity.registerPermission(
  onGranted: () -> Unit,
  onDenied: () -> Unit,
  onPermanentlyDenied: () -> Unit
) = Permission(registerForActivityResult(RequestMultiplePermissions()) {
  when (getPermissionState(this, it)) {
    PermissionState.Granted -> onGranted.invoke()
    PermissionState.Denied -> onDenied.invoke()
    PermissionState.PermanentlyDenied -> onPermanentlyDenied.invoke()
  }
})

fun Fragment.registerPermission(
  onGranted: () -> Unit,
  onDenied: () -> Unit,
  onPermanentlyDenied: () -> Unit
) = Permission(registerForActivityResult(RequestMultiplePermissions()) {
  when (getPermissionState(activity, it)) {
    PermissionState.Granted -> onGranted.invoke()
    PermissionState.Denied -> onDenied.invoke()
    PermissionState.PermanentlyDenied -> onPermanentlyDenied.invoke()
  }
})

fun Permission.launch(vararg permissions: String) = result.launch(arrayOf(*permissions))