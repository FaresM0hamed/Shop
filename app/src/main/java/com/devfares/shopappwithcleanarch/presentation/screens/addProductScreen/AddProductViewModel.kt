package com.devfares.shopappwithcleanarch.presentation.screens.addProductScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfares.shopappwithcleanarch.domain.models.DomainResult
import com.devfares.shopappwithcleanarch.domain.models.ProductModel
import com.devfares.shopappwithcleanarch.domain.usecases.AddProductUseCase
import com.devfares.shopappwithcleanarch.presentation.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    private val _addProductResult = MutableStateFlow<UiState<ProductModel>>(UiState.Idle)
    val addProductResult = _addProductResult.asStateFlow()

    fun postProduct(product: ProductModel) {
        viewModelScope.launch {
            _addProductResult.update { UiState.Loading }
            _addProductResult.update {
                UiState.Idle
                addProductUseCase(product).let {resultState->
                    when (resultState) {
                        is DomainResult.Error -> UiState.Error(resultState.error)
                        is DomainResult.Success -> UiState.Success(resultState.data)
                    }
                }
            }
        }
    }
}