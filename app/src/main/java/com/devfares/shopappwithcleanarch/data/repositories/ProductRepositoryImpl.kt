package com.devfares.shopappwithcleanarch.data.repositories

import android.util.Log
import com.devfares.shopappwithcleanarch.data.sources.remote.endpoints.ProductApiService
import com.devfares.shopappwithcleanarch.data.sources.remote.requestModels.ProductRequestModel
import com.devfares.shopappwithcleanarch.data.sources.remote.requestModels.toProductRequestModel
import com.devfares.shopappwithcleanarch.data.sources.remote.responseModels.toProductModel
import com.devfares.shopappwithcleanarch.domain.models.DomainResult
import com.devfares.shopappwithcleanarch.domain.models.ProductModel
import com.devfares.shopappwithcleanarch.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApi: ProductApiService) :
    ProductRepository {

    override suspend fun getProducts(): DomainResult<List<ProductModel>> = runCatching {
        DomainResult.Success(productApi.getProducts().map { it.toProductModel() })
    }.getOrElse {
        DomainResult.Error(it)
    }

    // TODO: note !!
    /**
     * Post method should be on data model not domain ?
     * */
    override suspend fun addProduct(product: ProductModel): DomainResult<ProductModel> {
        return runCatching {
            val addedProduct = productApi.addProduct(product)
            Log.d("ProductRepositoryImpl", "addProduct: $addedProduct")
            DomainResult.Success(addedProduct)
        }.getOrElse {
            DomainResult.Error(it)
        }
    }


}
