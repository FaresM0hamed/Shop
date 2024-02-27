package com.devfares.shopappwithcleanarch.domain.usecases

import com.devfares.shopappwithcleanarch.domain.repositories.ProductRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke() = repository.getProducts()
}