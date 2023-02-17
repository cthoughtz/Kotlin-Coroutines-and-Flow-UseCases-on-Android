package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class CalculationInBackgroundViewModel : BaseViewModel<UiState>() {

    fun performCalculation(factorialOf: Int) {
        uiState.value = UiState.Loading

        val result: BigInteger = BigInteger.ONE

        viewModelScope.launch {
            val computationDuration = measureTimeMillis {
                val result = calculateFactorialOf(factorialOf)
            }

            var resultString = ""
            val stringConversionDuration = measureTimeMillis {
                resultString = result.toString()
            }

            uiState.value = UiState.Success(resultString, computationDuration, stringConversionDuration)
        }
    }

    private fun calculateFactorialOf(number: Int): BigInteger {
        var factorial = BigInteger.ONE

        for (i in 1 .. number) {
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        return factorial
    }
}