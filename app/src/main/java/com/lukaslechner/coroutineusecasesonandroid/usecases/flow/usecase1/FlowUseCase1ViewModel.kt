package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class FlowUseCase1ViewModel(
    private val stockPriceDataSource: StockPriceDataSource
) : BaseViewModel<UiState>() {


    val currentStockPriceAsLiveData: LiveData<UiState> =  stockPriceDataSource
            .latestStockList
            .map {stockList ->
                UiState.Success(stockList) as UiState
            }
            .onStart {
                emit(UiState.Loading)
            }
            .onCompletion {
                Timber.tag("Flow").d("Flow has completed.")
            }
            .asLiveData()
}