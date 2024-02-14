package com.app.bank_misr.presentation.common

import com.app.twerlo.R
import com.app.bank_misr.domain.common.common.FailureType
import com.app.twerlo.presentation.common.UiText

fun FailureType.toUiText(): UiText
{
  return when(this)
  {
    FailureType.InvalidInput-> UiText.StringResource(R.string.network_invalid_input)
    FailureType.UnAuthorizedAccess-> UiText.StringResource(R.string.network_unauthorized)
    FailureType.Forbidden-> UiText.StringResource(R.string.network_forbidden)
    FailureType.NotFound-> UiText.StringResource(R.string.network_not_found)
    FailureType.ServerError-> UiText.StringResource(R.string.network_server_error)
    FailureType.TimeOut-> UiText.StringResource(R.string.network_connection_error)
    FailureType.ConnectionError-> UiText.StringResource(R.string.network_connection_error)
    FailureType.UnexpectedResponse-> UiText.StringResource(R.string.network_unexpected_response)
    FailureType.UnKnownError-> UiText.StringResource(R.string.network_unknown_error)
  }
}