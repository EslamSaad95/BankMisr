package com.app.bank_misr.data.repo

import com.app.bank_misr.data.network.ApiService
import com.app.bank_misr.domain.common.ApiResult
import com.app.bank_misr.domain.common.ErrorState
import com.app.bank_misr.domain.entity.CurrencyRatesEntity
import com.app.bank_misr.domain.entity.CurrencySymbolEntity
import com.app.bank_misr.domain.repo.CurrencyRepo
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val apiService: ApiService):CurrencyRepo {

  override suspend fun getCurrencies(): ApiResult<List<CurrencySymbolEntity>, ErrorState> {
    return try {
      val response = apiService.getCurrenciesSymbol()
      if (response.isSuccessful) {
        val symbols = ArrayList<CurrencySymbolEntity>()
        val gson = Gson()
        val jsonString = gson.toJson(response.body()?.symbols)
        val jsonObject = JSONObject(jsonString)
        jsonObject.keys().forEach {k->
          symbols.add(CurrencySymbolEntity(k, jsonObject.getString(k)))
        }
        ApiResult(value = symbols)
      }
      else {
        val error = response.errorBody()?.charStream()?.readText()
        error?.let {
          return ApiResult(error = ErrorState(response.code(), error.toString()))
        } ?: throw HttpException(response)
      }
    } catch (throwable: Throwable) {

      ApiResult(error = ErrorState(failureType = throwable.mapToFailureType()))
    }
  }

  override suspend fun getCurrenciesRates(): ApiResult<List<CurrencyRatesEntity>, ErrorState> {
    return try {
      val response = apiService.getCurrenciesRates()
      if (response.isSuccessful) {
        val rates = ArrayList<CurrencyRatesEntity>()
        val gson = Gson()
        val jsonString = gson.toJson(response.body())
        val jsonObject = JSONObject(jsonString)
        val ratesObject = jsonObject.getJSONObject("rates")
        ratesObject.keys().forEach { k ->
          rates.add(CurrencyRatesEntity(k, ratesObject.getDouble(k)))
        }
        ApiResult(value = rates)
      } else {
        val error = response.errorBody()?.charStream()?.readText()
        error?.let {
          return ApiResult(error = ErrorState(response.code(), error.toString()))
        } ?: throw HttpException(response)
      }
    } catch (throwable: Throwable) {
      ApiResult(error = ErrorState(message = throwable.message.toString()))
    }
  }
}