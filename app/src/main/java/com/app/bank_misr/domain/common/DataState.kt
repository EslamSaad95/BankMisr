package com.app.bank_misr.domain.common

import com.app.twerlo.presentation.common.UiText

sealed class DataState() {

  data object Idle : DataState()

  data class Loading(val fullScreen: Boolean) : DataState()

  data class Success<out T>(val result: T) : DataState()

  data class Error(val error: UiText) : DataState()
}