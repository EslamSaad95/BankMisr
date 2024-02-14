package com.app.bank_misr.data.network

import kotlin.jvm.Throws

data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null,
)
