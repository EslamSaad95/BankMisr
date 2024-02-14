package com.app.bank_misr.presentation.common
import com.app.bank_misr.R

sealed class ApiFailure(var message: UiText) {

  data class ApiError(
    val errorMessage: UiText
  ) : ApiFailure(message = errorMessage)

  data class InvalidInput(
    val errorMessage: UiText = UiText.StringResource(R.string.network_invalid_input)
  ) : ApiFailure(message = errorMessage)

  data class UnAuthorizedAccess(
    val errorMessage: UiText = UiText.StringResource(R.string.network_unauthorized)
  ) : ApiFailure(message = errorMessage)

  data class Forbidden(
    val errorMessage: UiText = UiText.StringResource(R.string.network_forbidden)
  ) : ApiFailure(message = errorMessage)

  data class NotFound(
    val errorMessage: UiText = UiText.StringResource(R.string.network_not_found)
  ) : ApiFailure(message = errorMessage)

  data class ServerError(
    val errorMessage: UiText = UiText.StringResource(R.string.network_server_error)
  ) : ApiFailure(message = errorMessage)

  data class TimeOut(
    val errorMessage: UiText = UiText.StringResource(R.string.network_connection_error)
  ) : ApiFailure(message = errorMessage)

  data class ConnectionError(
    val errorMessage: UiText = UiText.StringResource(R.string.network_connection_error)
  ) : ApiFailure(message = errorMessage)

  data class UnexpectedResponse(
    val errorMessage: UiText = UiText.StringResource(R.string.network_unexpected_response)
  ) : ApiFailure(message = errorMessage)

  data class UnKnownError(
    val errorMessage: UiText = UiText.StringResource(R.string.network_unknown_error)
  ) : ApiFailure(message = errorMessage)
}