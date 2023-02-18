package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.*

fun main() {

    val scope = CoroutineScope(Job())

    scope.launch {

            doSomeTask()

        launch {
            println("Starting Task 3")
            delay(300)
            println("Task 3 completed")
        }
    }
    Thread.sleep(1000)
}

suspend fun doSomeTask() = coroutineScope {
    launch {
        println("Starting Task 1")
        delay(100)
        println("Task 1 completed")
    }

    launch {
        println("Starting Task 2")
        delay(200)
        println("Task 2 completed")
    }

}