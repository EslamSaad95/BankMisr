package com.app.bank_misr.presentation.common.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 13/08/2023
 */
@Composable
fun SpannableCheckbox(
  text: String,
  spanText: String,
  checked: Boolean,
  onClick: () -> Unit,
  onCheckedChange: ((Boolean) -> Unit),
  modifier: Modifier = Modifier,
  spanStyle: SpanStyle = SpanStyle(textDecoration = TextDecoration.Underline),
) {

  Row(
    modifier = modifier,
    verticalAlignment = Alignment.Top
  ) {

    Checkbox(checked, onCheckedChange)

    SpannableText(
      text = text,
      spanText = spanText,
      spanStyle = spanStyle,
      style = normalTextStyle(),
      onClick = {
        onClick.invoke()
      }
    )
  }
}