package com.app.bank_misr.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.app.bank_misr.presentation.theme.CelestialBlue
import com.app.bank_misr.presentation.theme.SeaSalt
import com.app.bank_misr.presentation.theme.Silver
import com.intuit.ssp.R.dimen._10ssp
import com.intuit.ssp.R.dimen._12ssp
import com.intuit.ssp.R.dimen._13ssp
import com.intuit.ssp.R.dimen._9ssp

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 12/08/2023
 */

@Composable
fun normalTextStyle() = TextStyle(
  color = Color.Black,
  fontWeight = FontWeight.Normal,
  fontSize = dimensionResource(id = _12ssp).value.sp
)

@Composable
fun dropDownMenuColors() = TextFieldDefaults.colors(
  focusedContainerColor = SeaSalt,
  unfocusedContainerColor = SeaSalt,
  disabledContainerColor = SeaSalt,
  focusedIndicatorColor = Color.Transparent,
  unfocusedIndicatorColor = Color.Transparent,
  disabledIndicatorColor = Color.Transparent
)

@Composable
fun textFieldColors() = TextFieldDefaults.outlinedTextFieldColors(
  containerColor = Color.White,
  focusedBorderColor = Silver,
  unfocusedBorderColor = Silver,
  errorBorderColor = MaterialTheme.colorScheme.error,

  )

@Composable
fun disabledFieldColors() = TextFieldDefaults.colors(

  focusedContainerColor = SeaSalt,
  unfocusedContainerColor = SeaSalt,
  focusedIndicatorColor = Color.Transparent,
  unfocusedIndicatorColor = Color.Transparent,
  disabledIndicatorColor = Color.Transparent,
  disabledTextColor = Color.Transparent,
  disabledContainerColor = SeaSalt,
  disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
  disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
  disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
  disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
  disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
  disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
  disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant

)

@Composable
fun textFieldStyle() = TextStyle(
  color = Color.Black,
  fontSize = dimensionResource(id = _12ssp).value.sp,
  fontWeight = FontWeight.W500,
  textAlign = TextAlign.Start
)

@Composable
fun placeholderStyle() = TextStyle(
  color = Silver,
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start,
  fontSize = dimensionResource(id = _13ssp).value.sp
)



