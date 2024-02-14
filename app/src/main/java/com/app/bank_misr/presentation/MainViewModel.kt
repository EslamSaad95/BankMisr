package com.app.bank_misr.presentation

import androidx.lifecycle.ViewModel
import com.app.bank_misr.presentation.common.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

  private val _state by lazy { MutableStateFlow<DataState>(DataState.Idle) }
  val state get() = _state.asStateFlow()
}