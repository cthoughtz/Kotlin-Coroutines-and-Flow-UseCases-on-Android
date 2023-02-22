package com.lukaslechner.coroutineusecasesonandroid.playground.exceptionhandling

import kotlinx.coroutines.*

fun main() {

    val scope = CoroutineScope(Job())

    val deferred = scope.async {
        delay(200)
        throw java.lang.RuntimeException()
    }

    scope.launch {
        deferred.await()
    }

    Thread.sleep(500)
}
