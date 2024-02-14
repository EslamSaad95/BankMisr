package com.app.bank_misr.data.di

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
  abstract fun provideLoginRepo(repo: LoginRepositoryImpl): LoginRepo
}