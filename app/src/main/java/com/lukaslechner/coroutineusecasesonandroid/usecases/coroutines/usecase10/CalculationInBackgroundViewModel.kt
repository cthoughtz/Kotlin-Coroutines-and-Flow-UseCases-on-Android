package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.math.BigInteger
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading

        val result: BigInteger = BigInteger.ONE

        viewModelScope.launch {
            Timber.d("coroutine Context: $coroutineContext")

            val computationDuration = measureTimeMillis {
                val result = calculateFactorialOf(factorialOf)
            }

            var resultString = ""
            val stringConversionDuration = measureTimeMillis {
                resultString = withContext(Dispatchers.Default) {
                    result.toString()
                }
            }

            uiState.value = UiState.Success(resultString, computationDuration, stringConversionDuration)
        }
    }

       private suspend fun calculateFactorialOf(number: Int) = withContext(Dispatchers.Default) {
            var factorial = BigInteger.ONE

            for (i in 1..number) {
                factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
            }
        factorial
        }

    }