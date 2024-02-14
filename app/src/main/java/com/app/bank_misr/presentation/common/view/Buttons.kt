package com.app.bank_misr.presentation.common.view

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.bank_misr.presentation.theme.Charcoal
import com.app.bank_misr.presentation.theme.White
import com.intuit.sdp.R.dimen._10sdp
import com.intuit.sdp.R.dimen._40sdp
import com.intuit.ssp.R.dimen._14ssp

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 02/08/2023
 */
@Composable
fun ButtonFilled(
  @StringRes text: Int,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  color: Color = Charcoal,
  onClick: () -> Unit
) {
  Button(
    modifier = modifier
      .fillMaxWidth()
      .size(dimensionResource(id = _40sdp)),
    colors = ButtonDefaults.buttonColors(containerColor = color),
    enabled = enabled,
    shape = RoundedCornerShape(dimensionResource(id = _10sdp)),
    onClick = {
      onClick.invoke()
    }
  ) {

    Text(
      fontWeight = FontWeight.Medium,
      fontSize = dimensionResource(id = _14ssp).value.sp,
      text = stringResource(id = text),
      color = White
    )
  }
}
