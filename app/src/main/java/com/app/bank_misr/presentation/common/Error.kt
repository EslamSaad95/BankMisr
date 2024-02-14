package com.app.bank_misr.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.app.bank_misr.R
import com.app.bank_misr.presentation.theme.Black
import com.intuit.sdp.R.dimen._120sdp
import com.intuit.sdp.R.dimen._20sdp
import com.intuit.sdp.R.dimen._30sdp
import com.intuit.ssp.R.dimen._14ssp


@Composable
fun ErrorView(
  message: String,
  onRetry: (() -> Unit)? = null
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = dimensionResource(id = _20sdp)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Image(
      modifier = Modifier.size(dimensionResource(id = _120sdp)),
      painter = painterResource(id = R.drawable.ic_error),
      contentScale = ContentScale.FillWidth,
      contentDescription = null
    )

    Spacer(modifier = Modifier.height(dimensionResource(id = _20sdp)))

    Text(
      text = message,
      style = TextStyle(
        color = Black,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Center,
        fontSize = dimensionResource(id = _14ssp).value.sp
      )
    )

    onRetry?.let {
      Spacer(modifier = Modifier.height(dimensionResource(id = _30sdp)))

      ButtonFilled(text = R.string.error_retry) {
        onRetry.invoke()
      }
    }
  }
}