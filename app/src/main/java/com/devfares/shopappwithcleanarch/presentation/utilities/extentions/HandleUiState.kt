package com.devfares.shopappwithcleanarch.presentation.utilities.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.devfares.shopappwithcleanarch.presentation.models.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


fun <T> Fragment.handleUiState(
    uiState: UiState<T>,
    onSuccess: (T) -> Unit = {},
    onError: (Throwable) -> Unit = {},
) {
    when (uiState) {
        is UiState.Loading -> {
            activity?.startLoading()
        }
        is UiState.Error -> {
            activity?.stopLoading()
            onError(uiState.throwable)
        }
        is UiState.Success -> {
            activity?.stopLoading()
            onSuccess(uiState.data)
        }
        is UiState.Idle -> {
            activity?.stopLoading()
        }
    }
}

fun <T> Flow<T>.observeInLifecycle(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: (T) -> Unit
) {
    owner.lifecycleScope.launch {
        owner.lifecycle.repeatOnLifecycle(minActiveState) {
            collect { uiState ->
                collector(uiState)
            }
        }
    }
}

