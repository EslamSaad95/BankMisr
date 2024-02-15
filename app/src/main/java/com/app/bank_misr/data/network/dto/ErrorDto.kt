package com.app.bank_misr.data.network.dto

import com.google.gson.annotations.SerializedName

data class ErrorDto(
  @SerializedName("code")
  val code: Int,
  @SerializedName("info")
  val info: String
)
