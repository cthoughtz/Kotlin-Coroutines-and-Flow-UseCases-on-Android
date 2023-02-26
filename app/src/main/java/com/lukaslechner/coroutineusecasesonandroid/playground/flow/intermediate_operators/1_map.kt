package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

suspend fun main() {

    flowOf(1,2,3,4,5)
        .mapNotNull { "Emission $it" }
        .collect{collectValue ->
            println(collectValue)
        }
}