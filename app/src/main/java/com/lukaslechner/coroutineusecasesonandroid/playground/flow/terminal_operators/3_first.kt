package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {

    val flow = flow {
        kotlinx.coroutines.delay(1000)

        println("Emitting first value")
        emit(1)

        kotlinx.coroutines.delay(100)

        println("Emitting second value")
        emit(2)
    }

    runBlocking {
        val item = flow.first {it > 1}
        println("Received $item")
    }
}