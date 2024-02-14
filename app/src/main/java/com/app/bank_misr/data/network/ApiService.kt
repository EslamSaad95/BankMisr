package com.app.bank_misr.data.network

import com.app.bank_misr.data.network.dto.CurrenciesDto
import com.app.bank_misr.data.network.dto.CurrencySymbolDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

  @GET("symbols")
  suspend fun getCurrenciesSymbol(): Response<CurrencySymbolDto>
}