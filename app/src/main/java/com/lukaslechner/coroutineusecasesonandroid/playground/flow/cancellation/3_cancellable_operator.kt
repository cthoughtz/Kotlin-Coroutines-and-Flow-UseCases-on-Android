package com.lukaslechner.coroutineusecasesonandroid.playground.flow.cancellation

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main() {

    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        flowOf(1,2,3)
            .onCompletion { throwable ->
                if(throwable is CancellationException) {
                    println("Flow got cancelled.")
                }
            }.cancellable()
            .collect {
                println("Collected $it")

                if (it == 2) {
                    cancel()
                }
            }
    }.join()
}
