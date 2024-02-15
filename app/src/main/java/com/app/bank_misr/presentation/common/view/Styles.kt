package com.app.bank_misr.presentation.common.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.app.bank_misr.presentation.theme.Silver
import com.intuit.ssp.R.dimen._10ssp
import com.intuit.ssp.R.dimen._12ssp
import com.intuit.ssp.R.dimen._13ssp

@Composable
fun normalTextStyle() = TextStyle(
  color = Color.Black, fontWeight = FontWeight.Normal, fontSize = dimensionResource(id = _12ssp).value.sp
)

@Composable
fun textFieldColors() = OutlinedTextFieldDefaults.colors(
  focusedContainerColor = Color.White,
  unfocusedContainerColor = Color.White,
  disabledContainerColor = Color.White,
  focusedBorderColor = Silver,
  unfocusedBorderColor = Silver,
  errorBorderColor = MaterialTheme.colorScheme.error,
)

@Composable
fun textFieldStyle() = TextStyle(
  color = Color.Black,
  fontSize = dimensionResource(id = _10ssp).value.sp,
  fontWeight = FontWeight.W400,
  textAlign = TextAlign.Start
)

@Composable
fun placeholderStyle() = TextStyle(
  color = Silver,
  fontWeight = FontWeight.Normal,
  textAlign = TextAlign.Start,
  fontSize = dimensionResource(id = _13ssp).value.sp
)



