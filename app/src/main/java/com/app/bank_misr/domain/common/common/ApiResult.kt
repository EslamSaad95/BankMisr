package com.app.bank_misr.domain.common.common

data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null
)