package com.app.bank_misr.presentation.common

sealed class DataState {

  data object Idle : DataState()

  data class Loading(val fullScreen: Boolean) : DataState()

  data class Success<out T>(val result: T) : DataState()

  data class Error(val error: UiText) : DataState()
}