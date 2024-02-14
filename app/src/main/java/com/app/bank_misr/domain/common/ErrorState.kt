package com.app.bank_misr.domain.common

data class ErrorState(
  val statusCode:Int=0,
  val message:String?="",
)
