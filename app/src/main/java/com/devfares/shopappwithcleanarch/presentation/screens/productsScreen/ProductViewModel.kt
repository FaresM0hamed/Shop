package com.devfares.shopappwithcleanarch.presentation.screens.productsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfares.shopappwithcleanarch.domain.models.DomainResult
import com.devfares.shopappwithcleanarch.domain.models.ProductModel
import com.devfares.shopappwithcleanarch.domain.usecases.GetAllProductsUseCase
import com.devfares.shopappwithcleanarch.presentation.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _productsList = MutableStateFlow<UiState<List<ProductModel>>>(UiState.Idle)
    val productsList = _productsList.asStateFlow()

    init {
        loadProducts()
    }

     private fun loadProducts() {
        viewModelScope.launch {
            _productsList.update { UiState.Loading }
            _productsList.update {
                UiState.Idle
                getAllProductsUseCase().let {resultState->
                    when (resultState) {
                        is DomainResult.Error -> UiState.Error(resultState.error)
                        is DomainResult.Success -> UiState.Success(resultState.data)
                    }
                }
            }
        }
    }

}