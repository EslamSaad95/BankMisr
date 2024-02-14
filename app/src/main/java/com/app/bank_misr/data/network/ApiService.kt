package com.app.bank_misr.data.network

import com.app.bank_misr.data.network.dto.CurrenciesRatesDto
import com.app.bank_misr.data.network.dto.CurrencySymbolDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

  @GET("symbols")
  suspend fun getCurrenciesSymbol(): Response<CurrencySymbolDto>

  @GET("latest")
  suspend fun getCurrenciesRates():Response<CurrenciesRatesDto>
}