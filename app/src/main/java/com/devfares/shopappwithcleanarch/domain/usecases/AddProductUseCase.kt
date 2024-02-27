package com.devfares.shopappwithcleanarch.domain.usecases

import com.devfares.shopappwithcleanarch.domain.models.ProductModel
import com.devfares.shopappwithcleanarch.domain.repositories.ProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(product: ProductModel) = repository.addProduct(product)
}