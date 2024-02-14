package com.app.bank_misr.presentation.exception

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.app.bank_misr.data.util.findActivity
import com.app.bank_misr.presentation.MainActivity
import com.app.bank_misr.presentation.common.ButtonFilled
import com.app.bank_misr.presentation.theme.Black
import com.intuit.sdp.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ExceptionScreen() {
  val activity = LocalContext.current.findActivity()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {

    Image(
      painter = painterResource(id = com.app.bank_misr.R.drawable.ic_exception),
      contentDescription = ""
    )

    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._10sdp)))

    Text(
      color = Black,
      fontWeight = FontWeight.Medium,
      textAlign = TextAlign.Center,
      text = stringResource(id = com.app.bank_misr.R.string.exception_msg),
      fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._14ssp).value.sp
    )

    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._10sdp)))

    ButtonFilled(
      text = com.app.bank_misr.R.string.exception_restart,
      modifier = Modifier.padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._15sdp))
    ) {
      activity.startActivity(Intent(activity, MainActivity::class.java))
      activity.finishAffinity()
    }

    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._30sdp)))
  }
}