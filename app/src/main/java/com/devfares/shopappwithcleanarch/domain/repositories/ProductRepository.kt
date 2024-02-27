package com.devfares.shopappwithcleanarch.domain.repositories

import com.devfares.shopappwithcleanarch.domain.models.DomainResult
import com.devfares.shopappwithcleanarch.domain.models.ProductModel

interface ProductRepository {
    suspend fun getProducts(): DomainResult<List<ProductModel>>
    suspend fun addProduct(product: ProductModel): DomainResult<ProductModel>
}