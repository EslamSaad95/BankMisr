package com.app.bank_misr.presentation.common

import com.app.bank_misr.R
import com.app.bank_misr.domain.common.ErrorState

fun ErrorState.toUiText(): UiText
{
  return when(this.statusCode)
  {
    401-> UiText.StringResource(R.string.network_invalid_input)
    401-> UiText.StringResource(R.string.network_unauthorized)
    403-> UiText.StringResource(R.string.network_forbidden)
    404-> UiText.StringResource(R.string.network_not_found)
    500-> UiText.StringResource(R.string.network_server_error)
    else ->UiText.DynamicString(this.message.toString())
  }
}