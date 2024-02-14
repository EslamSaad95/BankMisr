package com.app.bank_misr.data.network

import com.app.bank_misr.data.network.dto.CurrenciesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

  @GET("symbol")
  suspend fun getCurrenciesSymbol(): Response<CurrenciesDto>
}