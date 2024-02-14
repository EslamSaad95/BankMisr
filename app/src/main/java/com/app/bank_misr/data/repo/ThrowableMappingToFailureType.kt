package com.app.bank_misr.data.repo

import com.app.bank_misr.domain.common.FailureType
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.mapToFailureType(): FailureType {
  when (this) {
    is HttpException -> {
      return when (this.code()) {
        400 -> FailureType.InvalidInput
        401 -> FailureType.UnAuthorizedAccess
        403 -> FailureType.Forbidden
        404 -> FailureType.NotFound
        500, 503 -> FailureType.ServerError
        else -> FailureType.ConnectionError
      }
    }

    is SocketTimeoutException, is IOException -> return FailureType.ConnectionError

  }
  return FailureType.UnKnownError
}