package com.app.bank_misr.data.network.dto

data class ApiResult<T, E>(
  val value: T? = null,
  val error: E? = null,
)
//AKNATONE
