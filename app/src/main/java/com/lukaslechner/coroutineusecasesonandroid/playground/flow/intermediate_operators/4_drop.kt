package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.*

suspend fun main() {

    flowOf(1,2,3,4,5)
        .dropWhile{it < 2}
        .collect{collectValue ->
            println(collectValue)
        }
}