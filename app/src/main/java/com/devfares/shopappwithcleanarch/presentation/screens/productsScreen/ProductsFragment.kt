package com.devfares.shopappwithcleanarch.presentation.screens.productsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devfares.shopappwithcleanarch.R
import com.devfares.shopappwithcleanarch.databinding.FragmentProductsBinding
import com.devfares.shopappwithcleanarch.presentation.utilities.extentions.handleUiState
import com.devfares.shopappwithcleanarch.presentation.utilities.extentions.observeInLifecycle
import com.devfares.shopappwithcleanarch.presentation.utilities.extentions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addProductFab.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_addProductFragment)
        }
        productsAdapter = ProductsAdapter(emptyList())
        binding.productsRecyclerView.adapter = productsAdapter
        productViewModel.productsList.observeInLifecycle(viewLifecycleOwner) { uiState ->
            handleUiState(uiState, onSuccess = {
                productsAdapter.updateList(it)
            }, onError = { errorMessage ->
                showToast("$errorMessage")
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

}