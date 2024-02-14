package com.app.bank_misr.domain.repo

import com.app.bank_misr.domain.common.ApiResult
import com.app.bank_misr.domain.common.ErrorState
import com.app.bank_misr.domain.entity.CurrencySymbolEntity

interface CurrencyRepo {

  suspend fun getCurrencies(): ApiResult<List<CurrencySymbolEntity>, ErrorState>
}