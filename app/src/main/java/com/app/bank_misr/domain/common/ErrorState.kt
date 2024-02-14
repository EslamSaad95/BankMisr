package com.app.bank_misr.domain.common

data class ErrorState(
  val statusCode: Int = 0,
  val message: String? = "",
  val failureType: FailureType? = null
)

enum class FailureType {
  InvalidInput, UnAuthorizedAccess, Forbidden, NotFound, ServerError, TimeOut, ConnectionError,
  UnexpectedResponse,
  UnKnownError
}
