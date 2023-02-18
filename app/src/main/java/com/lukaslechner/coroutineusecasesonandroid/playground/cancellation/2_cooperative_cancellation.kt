package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        yield()
        repeat(10) {index ->
            if (isActive) {
                println("operation number $index")
                Thread.sleep(100)
            } else {
                throw CancellationException()
            }
        }
    }

    delay(250)
    println("Cancelling Coroutine")
    job.cancel()
}