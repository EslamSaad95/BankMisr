package com.app.bank_misr.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bank_misr.data.local_storage.prefs.PrefStore
import com.app.bank_misr.presentation.common.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val useCase: LoginUseCase,
  private val prefStore: PrefStore
) : ViewModel() {

  private val _state = MutableStateFlow<DataState>(DataState.Idle)
  val state get() = _state

  val userName = mutableStateOf("")
  val password = mutableStateOf("")

  val userNameError = mutableStateOf<UiText>(UiText.Empty)
  val passwordError = mutableStateOf<UiText>(UiText.Empty)
  val passwordVisible = mutableStateOf(false)

  fun login() {

    if (validEntries())
      viewModelScope.launch {
        state.value = DataState.Loading(fullScreen = false)
        useCase.login(userName.value, password.value).collect { loginUseCase ->
          loginUseCase.value?.userToken?.let { userToken ->
            _state.value = DataState.Success(userToken)
            prefStore.setUserToken(userToken)
          }
          loginUseCase.error?.let { errorState ->
            if(errorState.message.isNullOrEmpty().not())
              state.value = DataState.Error(UiText.DynamicString(errorState.message.toString()))
          }
        }

      }
  }

  private fun validEntries(): Boolean {
    if (userName.value.isEmpty()) {
      userNameError.value = UiText.StringResource(com.app.bank_misr.R.string.validations_user_name_empty)
      return false
    }

    if (password.value.isEmpty()) {
      passwordError.value = UiText.StringResource(com.app.bank_misr.R.string.validations_password_empty)
      return false
    }

    return true
  }
}