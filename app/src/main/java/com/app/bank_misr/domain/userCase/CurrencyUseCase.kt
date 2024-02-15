package com.app.bank_misr.domain.userCase

import com.app.bank_misr.domain.repo.CurrencyRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(private val repo: CurrencyRepo) {

  suspend fun getCurrenciesSymbols() = flow {
    emit(repo.getCurrencies())
  }

  suspend fun getCurrenciesRates() = flow {
    emit(repo.getCurrenciesRates())
  }
}