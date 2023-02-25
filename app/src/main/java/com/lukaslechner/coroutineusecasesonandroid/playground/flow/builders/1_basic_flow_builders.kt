package com.lukaslechner.coroutineusecasesonandroid.playground.flow.builders

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

suspend fun main() {

    val firstFlow = flowOf<Int>(1).collect {emittedValue ->
        println("firstFlow: $emittedValue")
    }

    val secondFlow = flowOf(1,2,4)

     secondFlow.collect {emittedValues ->
        println("secondFlow: $emittedValues")
    }

    listOf("A","B","C").asFlow().collect{emittedValue ->
        println("asFlow $emittedValue")
    }

    flow {
        delay(2000)
        emit("item emitted after 2000ms")

        secondFlow.collect{emitValue ->
            emit(emitValue)
        }

    }.collect{emittedValue ->
        println("flow{}: $emittedValue")
    }
}