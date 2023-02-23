package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase13

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.*
import retrofit2.HttpException
import timber.log.Timber

class ExceptionHandlingViewModel(
    private val api: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun handleExceptionWithTryCatch() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                api.getAndroidVersionFeatures(27)
            } catch (exception: Exception) {
                if(exception is HttpException) {
                    if(exception.code() == 500) {
                        // Error Message 1
                    } else {
                        // Error Message 2
                    }
                }
                uiState.value = UiState.Error("Network Request failed: $exception")
            }
        }
    }

    fun handleWithCoroutineExceptionHandler() {
        uiState.value = UiState.Loading

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            uiState.value = UiState.Error("Network Request failed!")
        }

        viewModelScope.launch(exceptionHandler) {
            api.getAndroidVersionFeatures(27)
        }
    }

    fun showResultsEvenIfChildCoroutineFails() {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            supervisorScope {
                val oreoFeaturesDeferred = async {
                    api.getAndroidVersionFeatures(27)
                }

                val pieFeaturesDeferred = async {
                    api.getAndroidVersionFeatures(28)
                }

                val android10FeaturesDeferred = async {
                    api.getAndroidVersionFeatures(29)
                }



                val versionFeatures = listOfNotNull(oreoFeaturesDeferred,pieFeaturesDeferred,android10FeaturesDeferred)
                    .map {
                        try {
                            it.await()
                        } catch (e: Exception) {
                            if(e is CancellationException) {
                                throw e
                            }
                            Timber.e("Error loading feature data!")
                            null
                        }
                    }.filterNotNull()

                uiState.value = UiState.Success(versionFeatures)
            }
        }

        }
    }