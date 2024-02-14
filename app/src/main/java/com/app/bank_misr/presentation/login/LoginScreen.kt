package com.app.bank_misr.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.bank_misr.data.util.cast
import com.app.bank_misr.domain.common.DataState
import com.app.bank_misr.presentation.common.view.ButtonFilled
import com.app.bank_misr.presentation.common.view.ErrorAlert
import com.app.bank_misr.presentation.common.view.LoadingDialog
import com.app.bank_misr.presentation.common.view.OutLineTextInput
import com.app.bank_misr.presentation.common.view.OutlinePasswordTextField
import com.app.bank_misr.presentation.common.UiText
import com.app.bank_misr.presentation.destinations.MainScreenDestination
import com.intuit.sdp.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("StateFlowValueCalledInComposition")
@Destination()
@RootNavGraph(start = true)
@Composable
fun LoginScreen(
  navigator: DestinationsNavigator? = null,
  viewModel: LoginViewModel = hiltViewModel()
) {
  val state by viewModel.state.collectAsState()

  when (state) {
    is DataState.Idle -> {}
    is DataState.Loading -> {
      LoadingDialog()
    }

    is DataState.Success<*> -> {
      navigator?.navigate(MainScreenDestination)

      viewModel.state.value = DataState.Idle
    }

    is DataState.Error -> {
      val error = state.cast<DataState.Error>().error.asString()
      ErrorAlert(
        message = error,
        confirmText = stringResource(id = com.app.bank_misr.R.string.alert_ok)
      )
    }

  }
  LoginContent(viewModel = viewModel)
}
  @Composable
  fun LoginContent(viewModel: LoginViewModel)
  {
  Scaffold(
    content = { innerPadding ->
     Column(
       verticalArrangement = Arrangement.Center
       ,modifier = Modifier
         .padding(top = innerPadding.calculateTopPadding())
         .fillMaxSize()
         .verticalScroll(rememberScrollState())) {

       OutLineTextInput(
         keyboardType = KeyboardType.Text,
         isError = viewModel.userNameError.value != UiText.Empty,
         value = viewModel.userName.value,
         modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
         placeholder = com.app.bank_misr.R.string.user_name,
         errorMessage = viewModel.userNameError.value.asString(),
         onValueChange = {
           viewModel.userName.value = it
           viewModel.userNameError.value = UiText.Empty
         }
       )
       Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._4sdp)))

       OutlinePasswordTextField(
         keyboardType = KeyboardType.Password,
         isError = viewModel.passwordError.value != UiText.Empty,
         value = viewModel.password.value,
         placeholder = com.app.bank_misr.R.string.password,
         modifier = Modifier
           .fillMaxWidth()
           .padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
         errorMessage = viewModel.passwordError.value.asString(),
         onValueChange = {
           viewModel.password.value = it
           viewModel.passwordError.value = UiText.Empty
         },
         passwordVisible = viewModel.passwordVisible
       )

       Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._15sdp)))
       ButtonFilled(
         text = com.app.bank_misr.R.string.login,
         modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
         onClick =
         {
           viewModel.login()
         })
     }
    }
  )
}
