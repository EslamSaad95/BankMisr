package com.app.bank_misr.data.network

import com.app.bank_misr.data.network.dto.CurrenciesDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


  @GET("latest")
  suspend fun getLatestCurrencies():Response<CurrenciesDto>

}