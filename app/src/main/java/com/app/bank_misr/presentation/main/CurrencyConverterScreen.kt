package com.app.bank_misr.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.bank_misr.R
import com.app.bank_misr.data.util.cast
import com.app.bank_misr.domain.entity.CurrencySymbolEntity
import com.app.bank_misr.presentation.common.DataState
import com.app.bank_misr.presentation.common.UiText
import com.app.bank_misr.presentation.common.view.CurrencySymbolsOutlineDropDown
import com.app.bank_misr.presentation.common.view.ErrorView
import com.app.bank_misr.presentation.common.view.LoadingDialog
import com.app.bank_misr.presentation.common.view.OutLineTextInput
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun CurrencyConverterScreen(
  viewModel: CurrencyConverterViewModel = hiltViewModel(),
) {
  val state by viewModel.state.collectAsState()
  var data by remember { mutableStateOf<List<CurrencySymbolEntity>?>(null) }


  when (state) {
    is DataState.Idle -> {}
    is DataState.Loading -> {
      LoadingDialog()
    }

    is DataState.Success<*> ->

        data = state.cast<DataState.Success<List<CurrencySymbolEntity>>>().result


    is DataState.Error -> {
      val error = state.cast<DataState.Error>().error.asString()
      ErrorView(message = error) {
        if (viewModel.action == CurrencyConverterViewModel.Actions.CURRENCY_SYMBOLS)
          viewModel.getCurrenciesSymbol()
        else
          viewModel.getCurrenciesRates()
      }
    }
  }

  data?.let { CurrencyConverterContent(it, viewModel) }
}

@Composable
fun CurrencyConverterContent(symbols: List<CurrencySymbolEntity>, viewModel: CurrencyConverterViewModel) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)),
    verticalArrangement = Arrangement.Center
  ) {

    Row(
      modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      CurrencySymbolsOutlineDropDown(
        modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._100sdp)),
        value = viewModel.fromCurrency.value?.symbol ?: "",
        placeholder = R.string.from_currency,
        errorMessage = viewModel.fromCurrencyError.value.asString(),
        onValueChange = {
          viewModel.fromCurrencyError.value = UiText.Empty
        }, currencySymbol = symbols, onCurrencyChanged = {
          viewModel.fromCurrency.value = it
          if (viewModel.toCurrency.value != null && viewModel.toCurrencyAmount.value.isEmpty().not()) {
            viewModel.fromCurrencyAmount.value = viewModel.convertCurrency(viewModel.toCurrencyAmount.value)
          }
        }
      )
      Icon(
        painter = painterResource(id = R.drawable.ic_swap),
        tint = Color.Unspecified,
        contentDescription = null,
        modifier = Modifier
          .size(dimensionResource(id = com.intuit.sdp.R.dimen._40sdp))
          .clickable {
            val tmp = viewModel.fromCurrency
            viewModel.fromCurrency.value = viewModel.toCurrency.value
            viewModel.toCurrency.value = tmp.value
          }
      )

      CurrencySymbolsOutlineDropDown(
        modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._100sdp)),
        value = viewModel.toCurrency.value?.symbol ?: "",
        placeholder = R.string.to_currency,
        errorMessage = viewModel.toCurrencyError.value.asString(),
        onValueChange = {
          viewModel.toCurrencyError.value = UiText.Empty
        },
        currencySymbol = symbols,
        onCurrencyChanged = {
          viewModel.toCurrency.value = it
          if (viewModel.fromCurrency.value != null && viewModel.fromCurrencyAmount.value.isEmpty().not()) {
            viewModel.toCurrencyAmount.value = viewModel.convertCurrency(viewModel.fromCurrencyAmount.value)
          }
        }
      )
    }

    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)))

    Row(
      modifier = Modifier
        .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._16sdp))
        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      OutLineTextInput(
        keyboardType = KeyboardType.Number,
        isError = viewModel.fromCurrencyAmountError.value != UiText.Empty,
        value = viewModel.fromCurrencyAmount.value,
        modifier = Modifier
          .width(dimensionResource(id = com.intuit.sdp.R.dimen._70sdp)),
        placeholder = R.string.amount,
        errorMessage = viewModel.fromCurrencyAmountError.value.asString(),
        onValueChange = {
          viewModel.fromCurrencyAmount.value = it
          viewModel.fromCurrencyError.value = UiText.Empty
          if (viewModel.fromCurrency.value != null && viewModel.toCurrency.value != null) {
            if (it.isNotEmpty())
              viewModel.toCurrencyAmount.value = viewModel.convertCurrency(viewModel.fromCurrencyAmount.value)
          }
        }
      )
      OutLineTextInput(
        keyboardType = KeyboardType.Text,
        isError = viewModel.toCurrencyAmountError.value != UiText.Empty,
        value = viewModel.toCurrencyAmount.value,
        modifier = Modifier
          .width(dimensionResource(id = com.intuit.sdp.R.dimen._70sdp)),
        placeholder = R.string.amount,
        errorMessage = viewModel.toCurrencyError.value.asString(),
        onValueChange = {
          viewModel.toCurrencyAmount.value = it
          viewModel.toCurrencyError.value = UiText.Empty
          if (viewModel.fromCurrency.value != null && viewModel.toCurrency.value != null) {
            if (it.isNotEmpty())
              viewModel.fromCurrencyAmount.value = viewModel.convertCurrency(viewModel.toCurrencyAmount.value)
          }
        }
      )
    }
  }
}