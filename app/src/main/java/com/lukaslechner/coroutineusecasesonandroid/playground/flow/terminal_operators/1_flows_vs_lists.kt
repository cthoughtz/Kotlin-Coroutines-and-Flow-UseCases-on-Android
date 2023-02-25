package com.lukaslechner.coroutineusecasesonandroid.playground.flow.terminal_operators

import kotlinx.coroutines.flow.flow

fun main() {

    val flow = flow {
        kotlinx.coroutines.delay(1000)

        println("Emitting first value")
        emit(1)

        kotlinx.coroutines.delay(100)

        println("Emitting second value")
        emit(2)
    }

    val list = buildList {
        add(1)
        println("add 1 to list")

        add(2)
        println("add 2 to list")
    }
}