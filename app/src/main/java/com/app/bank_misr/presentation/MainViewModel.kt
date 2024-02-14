package com.app.bank_misr.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

  private val _state by lazy { MutableStateFlow<DataState>(Idle) }
  val state get() = _state.asStateFlow()
}