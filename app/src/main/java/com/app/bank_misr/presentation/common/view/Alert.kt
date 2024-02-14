package com.app.bank_misr.presentation.common.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.app.bank_misr.R
import com.app.bank_misr.presentation.theme.AppTheme
import com.app.bank_misr.presentation.theme.Black
import kotlinx.coroutines.delay


@Composable
fun Alert(
  title: String? = stringResource(id = R.string.app_name),
  message: String,
  confirmText: String,
  cancelText: String? = null,
  onConfirm: () -> Unit = {},
  onDismiss: () -> Unit = {},
  closeAutomatic: Boolean = false
) {

  val openDialog = remember { mutableStateOf(true) }

  if (closeAutomatic) {
    LaunchedEffect(openDialog) {
      delay(2500)
      onConfirm.invoke()
      openDialog.value = false
    }
  }

  if (openDialog.value) {
    AlertDialog(
      title = title?.let { { if (title.isNotEmpty()) Text(text = title) } },
      text = { Text(message)},
      confirmButton = {
        TextButton(
          onClick = {
            openDialog.value = false
            onConfirm.invoke()
          }) {
          Text(
            confirmText, color = Black,
          )
        }
      },
      dismissButton = {
        cancelText?.let {
          TextButton(
            onClick = {
              openDialog.value = false
              onDismiss.invoke()
            }) {
            Text(
              cancelText, color = Black,
            )
          }
        }
      },
      onDismissRequest = {
        openDialog.value = false
        onDismiss.invoke()
      },
      properties = DialogProperties(
        dismissOnClickOutside = false,
        dismissOnBackPress = false,
      )
    )
  }
}



@Composable
fun ErrorAlert(
  message: String,
  confirmText: String,
  cancelText: String? = null,
  onConfirm: () -> Unit = {},
  onDismiss: () -> Unit = {},
  closeAutomatic: Boolean = false,
  disabledFontValue: Boolean = false
) {

  val openDialog = remember { mutableStateOf(true) }

  if (closeAutomatic) {
    LaunchedEffect(openDialog) {
      delay(2500)
      onConfirm.invoke()
      openDialog.value = false
    }
  }

  if (openDialog.value) {
    AlertDialog(
      title = {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
          Icon(
            painter = painterResource(id = R.drawable.ic_error),
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._50sdp))
          )
        }

      },
      text = {
        Text(
          message,
          color = Black,
          modifier = Modifier.fillMaxWidth(),
          fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp,
          fontWeight = FontWeight.W500,
          textAlign = TextAlign.Center,
        )
      },
      confirmButton = {
        TextButton(
          onClick = {
            openDialog.value = false
            onConfirm.invoke()
          }) {
          Text(
            confirmText, color = Black,
          )
        }
      },
      dismissButton = {
        cancelText?.let {
          TextButton(
            onClick = {
              openDialog.value = false
              onDismiss.invoke()
            }) {
            Text(
              cancelText, color = Black,
            )
          }
        }
      },
      onDismissRequest = {
        openDialog.value = false
        onDismiss.invoke()
      },
      properties = DialogProperties(
        dismissOnClickOutside = false,
        dismissOnBackPress = false,
      )
    )
  }
}



@Preview(showBackground = true)
@Composable
fun AlertPreview() {
  AppTheme {
    ErrorAlert(
      message = "This is a preview of alert",
      confirmText = "OK",
      cancelText = "Cancel",
      onConfirm = {},
      onDismiss = {}
    )
  }
}