package com.app.bank_misr.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bank_misr.domain.entity.CurrencyRatesEntity
import com.app.bank_misr.domain.entity.CurrencySymbolEntity
import com.app.bank_misr.domain.userCase.CurrencyUseCase
import com.app.bank_misr.presentation.common.DataState
import com.app.bank_misr.presentation.common.UiText
import com.app.bank_misr.presentation.common.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(private val useCase: CurrencyUseCase) : ViewModel() {

  private val _state = MutableStateFlow<DataState>(DataState.Idle)
  val state get() = _state

  val fromCurrencyError = mutableStateOf<UiText>(UiText.Empty)
  val toCurrencyError = mutableStateOf<UiText>(UiText.Empty)

  val fromCurrency = mutableStateOf<CurrencySymbolEntity?>(null)
  val toCurrency = mutableStateOf<CurrencySymbolEntity?>(null)

  val fromCurrencyAmount = mutableStateOf("1")
  val fromCurrencyAmountError = mutableStateOf<UiText>(UiText.Empty)
  val toCurrencyAmount = mutableStateOf<String>("")
  val toCurrencyAmountError = mutableStateOf<UiText>(UiText.Empty)

  private val currencyRates = MutableStateFlow<List<CurrencyRatesEntity>?>(null)

  var action = Actions.CURRENCY_SYMBOLS

  enum class Actions {
    CURRENCY_RATES,
    CURRENCY_SYMBOLS,
  }

  fun convertCurrency() {
    if (fromCurrency.value != null && toCurrency.value != null && fromCurrencyAmount.value.isNotEmpty()
    ) {
      currencyRates.value?.let { rates ->
        val result = (fromCurrencyAmount.value.toInt() *
            rates.filter { it.symbol == fromCurrency.value?.symbol }[0].rates) /
            rates.filter { it.symbol == toCurrency.value?.symbol }[0].rates
        toCurrencyAmount.value = result.toString()
      }
    }
  }

  init {
    getCurrenciesSymbol()
    getCurrenciesRates()
  }

  fun getCurrenciesSymbol() {
    action = Actions.CURRENCY_SYMBOLS
    viewModelScope.launch {
      state.value = DataState.Loading(fullScreen = false)
      useCase.getCurrenciesSymbols().collect { currenciesResponse ->
        currenciesResponse.value?.let { currenciesSymbols ->
          _state.value = DataState.Success(currenciesSymbols)
        }

        currenciesResponse.error?.let { errorState ->
          if (errorState.message.isNullOrEmpty().not())
            state.value = DataState.Error(errorState.toUiText())
          else if (errorState.failureType != null) {
            state.value = DataState.Error(errorState.failureType.toUiText())
          }
        }

      }
    }
  }

  fun getCurrenciesRates() {
    action = Actions.CURRENCY_RATES
    viewModelScope.launch {
      useCase.getCurrenciesRates().collect { currenciesResponse ->
        currenciesResponse.value?.let { rates ->
          currencyRates.value = rates
        }
        currenciesResponse.error?.let { errorState ->
          if (errorState.message.isNullOrEmpty().not())
            state.value = DataState.Error(errorState.toUiText())
          else if (errorState.failureType != null) {
            state.value = DataState.Error(errorState.failureType.toUiText())
          }
        }
      }
    }
  }
}