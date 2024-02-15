package com.app.bank_misr.presentation.common.view

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.app.bank_misr.domain.entity.CurrencySymbolEntity
import com.intuit.sdp.R

@Composable
fun OutLineTextInput(
  value: String,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  maxLength: Int = Int.MAX_VALUE,
  keyboardType: KeyboardType = KeyboardType.Text,
  colors: TextFieldColors = textFieldColors(),
  imeAction: ImeAction = ImeAction.Default,
  isError: Boolean = false,
) {

  OutlinedTextField(
    modifier = modifier,
    shape = RoundedCornerShape(dimensionResource(id = R.dimen._6sdp)),
    singleLine = true,
    enabled = true,
    isError = isError,
    value = value,
    onValueChange = {
      if (it.length <= maxLength) onValueChange.invoke(it)
    },
    placeholder = {
      Text(
        text = stringResource(id = placeholder), style = placeholderStyle()
      )
    },
    supportingText = {
      if (errorMessage.isNotEmpty()) {
        Text(
          text = errorMessage,
          color = MaterialTheme.colorScheme.error,
        )
      }
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType, imeAction = imeAction
    ),
    colors = colors,
    textStyle = textFieldStyle(),
  )
}

@Composable
fun CurrencySymbolsOutlineDropDown(
  modifier: Modifier = Modifier,
  value: String,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  currencySymbol: List<CurrencySymbolEntity>,
  onCurrencyChanged: (CurrencySymbolEntity) -> Unit,
  isError: Boolean = false
) {

  var expanded by remember { mutableStateOf(false) }
  var selectedIndex by remember { mutableIntStateOf(0) }

  Box(
    modifier = Modifier.wrapContentSize()
  ) {

    DropDownOutLineTextInput(modifier = modifier,
      value = value,
      placeholder = placeholder,
      errorMessage = errorMessage,
      isError = isError,
      onValueChange = onValueChange,
      onClick = {
        expanded = true
      })

    Icon(
      painter = painterResource(id = com.app.bank_misr.R.drawable.ic_arrow_down),
      tint = Color.Unspecified,
      contentDescription = null,
      modifier = Modifier
        .align(alignment = Alignment.TopEnd)
        .padding(top = dimensionResource(id = R.dimen._15sdp))
        .padding(horizontal = dimensionResource(id = R.dimen._5sdp))
    )

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
      currencySymbol.forEachIndexed { index, currency ->
        DropdownMenuItem(text = {
          Text(
            text = currency.name, style = normalTextStyle()
          )
        }, onClick = {
          selectedIndex = index
          onCurrencyChanged.invoke(currency)
          expanded = false
        })

        if (index < currencySymbol.lastIndex) Divider()
      }
    }

  }
}

@Composable
fun DropDownOutLineTextInput(
  value: String,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  maxLength: Int = Int.MAX_VALUE,
  keyboardType: KeyboardType = KeyboardType.Text,
  colors: TextFieldColors = textFieldColors(),
  imeAction: ImeAction = ImeAction.Default,
  isError: Boolean = false,
  onClick: () -> Unit,
) {

  OutlinedTextField(
    modifier = modifier.clickable { onClick.invoke() },
    shape = RoundedCornerShape(dimensionResource(id = R.dimen._6sdp)),
    singleLine = true,
    readOnly = true,
    isError = isError,
    enabled = false,
    value = value,
    onValueChange = {
      if (it.length <= maxLength) onValueChange.invoke(it)
    },
    placeholder = {
      Text(
        text = stringResource(id = placeholder), style = placeholderStyle()
      )
    },
    supportingText = {
      if (errorMessage.isNotEmpty()) {
        Text(
          text = errorMessage, color = MaterialTheme.colorScheme.error
        )
      }
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType, imeAction = imeAction
    ),
    colors = colors,
    textStyle = textFieldStyle(),
  )
}