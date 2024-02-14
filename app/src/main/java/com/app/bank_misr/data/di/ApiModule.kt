package com.app.bank_misr.data.di

import com.app.bank_misr.data.repo.CurrencyRepositoryImpl
import com.app.bank_misr.domain.repo.CurrencyRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

  @Singleton
  @Binds
  abstract fun provideCurrencyRepo(repo: CurrencyRepositoryImpl): CurrencyRepo
}