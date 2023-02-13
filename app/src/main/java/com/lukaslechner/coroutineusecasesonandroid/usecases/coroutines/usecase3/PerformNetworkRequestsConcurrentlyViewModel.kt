package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.launch

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        try {
            uiState.value = UiState.Loading
            viewModelScope.launch {
                val oreoFeature = mockApi.getAndroidVersionFeatures(27)
                val pieFeatures = mockApi.getAndroidVersionFeatures(28)
                val android10Features = mockApi.getAndroidVersionFeatures(29)

                val versionFeatures = listOf(oreoFeature,pieFeatures,android10Features)
                uiState.value = UiState.Success(versionFeatures)
            }
        } catch (exception: Exception) {
            uiState.value = UiState.Error("Network Request failed")
        }
    }

    fun performNetworkRequestsConcurrently() {

    }
}