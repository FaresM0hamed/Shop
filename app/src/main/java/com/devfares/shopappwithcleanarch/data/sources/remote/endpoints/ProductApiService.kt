package com.devfares.shopappwithcleanarch.data.sources.remote.endpoints


import com.devfares.shopappwithcleanarch.data.sources.remote.requestModels.ProductRequestModel
import com.devfares.shopappwithcleanarch.data.sources.remote.responseModels.ProductResponseModel
import com.devfares.shopappwithcleanarch.domain.models.ProductModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponseModel>
    @POST("products")
    suspend fun addProduct(@Body product: ProductModel): ProductModel

}