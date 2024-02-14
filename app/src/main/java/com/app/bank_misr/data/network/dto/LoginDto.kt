package com.app.bank_misr.data.network.dto

import com.google.gson.annotations.SerializedName

data class LoginDto(
  @SerializedName("token")
  val token:String?
)
