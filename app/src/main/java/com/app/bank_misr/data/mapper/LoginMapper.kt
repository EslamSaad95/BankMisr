package com.app.bank_misr.data.mapper

import com.app.bank_misr.data.network.dto.LoginDto
import com.app.bank_misr.domain.entity.LoginEntity

fun LoginDto.toLoginEntity():LoginEntity{
  return LoginEntity(this.token)
}