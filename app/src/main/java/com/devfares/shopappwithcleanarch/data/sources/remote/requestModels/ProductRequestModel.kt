package com.devfares.shopappwithcleanarch.data.sources.remote.requestModels

import com.devfares.shopappwithcleanarch.domain.models.ProductModel

data class ProductRequestModel(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val category: String? = null

)

fun ProductModel.toProductRequestModel() = ProductRequestModel(
    category = category,
    description = description,
    id = id,
    price = price,
    title = title
)