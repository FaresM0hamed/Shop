package com.devfares.shopappwithcleanarch.data.sources.remote.responseModels


import com.devfares.shopappwithcleanarch.domain.models.ProductModel
import com.google.gson.annotations.SerializedName

data class ProductResponseModel(
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("price") val price: Double,
    @SerializedName("rating") val rating: Rating,
    @SerializedName("title") val title: String
)

data class Rating(
    @SerializedName("count") val count: Int, @SerializedName("rate") val rate: Double
)

fun ProductResponseModel.toProductModel() = ProductModel(
    category = category,
    description = description,
    id = id,
    image = image,
    price = price,
    rating = rating.toRating(),
    title = title
)

private fun Rating.toRating() = com.devfares.shopappwithcleanarch.domain.models.Rating(
    count = count, rate = rate
)

