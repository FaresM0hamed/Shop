package com.devfares.shopappwithcleanarch.data.di

import com.devfares.shopappwithcleanarch.data.repositories.ProductRepositoryImpl
import com.devfares.shopappwithcleanarch.domain.repositories.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideProductsRepository(productRepository: ProductRepositoryImpl): ProductRepository {
        return productRepository
    }
}