package com.app.bank_misr.presentation

import androidx.lifecycle.ViewModel
import com.app.bank_misr.domain.common.DataState
import com.app.bank_misr.domain.common.DataState.Idle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

  private val _state by lazy { MutableStateFlow<DataState>(Idle) }
  val state get() = _state.asStateFlow()
}