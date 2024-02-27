package com.devfares.shopappwithcleanarch.presentation.screens.addProductScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.devfares.shopappwithcleanarch.databinding.FragmentAddProductBinding
import com.devfares.shopappwithcleanarch.domain.models.ProductModel
import com.devfares.shopappwithcleanarch.presentation.utilities.extentions.handleUiState
import com.devfares.shopappwithcleanarch.presentation.utilities.extentions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddProductFragment : Fragment() {
    private val viewModel: AddProductViewModel by viewModels()
    private lateinit var binding: FragmentAddProductBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAddProduct.setOnClickListener {
            val productName = binding.editTextProductName.text.toString()
            val productPrice = binding.editTextProductPrice.text.toString().toDouble()
            val productDescription = binding.editTextProductDescription.text.toString()
            val product = ProductModel(
                description = productDescription, title = productName, price = productPrice
            )
            viewModel.postProduct(product)
            lifecycleScope.launch {
                viewModel.addProductResult.collect { uiState ->
                    handleUiState(uiState, onSuccess = {
                        showToast("Product added successfully")
                    }, onError = { errorMessage ->
                        showToast("$errorMessage")
                    })
                }
            }
            // TODO: note!!
            /**
             * every time fragment started the toast is shown,but the post request is not sent until the button is clicked
             * */
//            viewModel.addProductResult.observeInLifecycle(viewLifecycleOwner) { uiState ->
//                handleUiState(uiState, onSuccess = {
//                   showToast("Product added successfully")
//                }, onError = { errorMessage ->
//                    showToast("$errorMessage")
//                })
//            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

}