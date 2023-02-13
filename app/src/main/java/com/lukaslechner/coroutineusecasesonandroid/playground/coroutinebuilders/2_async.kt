package com.lukaslechner.coroutineusecasesonandroid.playground.coroutinebuilders

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {

    val startTime = System.currentTimeMillis()

    val deferred1 = async(start = CoroutineStart.LAZY) {
        val result1 = networkCall(1)
        println("result received: result1 after ${elapsedMillis(startTime)}")
        result1
    }

    val deferred2 = async {
        val deferred2 = networkCall(2)
        println("result received: result1 after ${elapsedMillis(startTime)}")
        deferred2
    }

    deferred1.start()

    val resultList = listOf(deferred1.await(),deferred2.await())
    println("Result list: $resultList after ${elapsedMillis(startTime)}ms")
}

suspend fun networkCall (number: Int): String {
    delay(500)
    return "Result $number"
}

fun elapsedMillis(startTime: Long) = System.currentTimeMillis() - startTime