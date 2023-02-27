package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
        val stockFlow = stocksFlow()

            stockFlow
                .onCompletion { cause ->
                    if(cause == null) {
                        println("Flow completed successfully!")
                    } else {
                        println("Flow completed exceptionally with $cause")
                    }
                }
                .catch { throwable ->
                    println("Handle exception in catch() operator $throwable")
                }
                .onEach {stock ->
                    throw Exception(" Exception in collect{}")
                    println("Collected $stock")
                }
                .catch { throwable ->
                    println("Handle exception in catch() operator $throwable")
                }
                .launchIn(this)

}

private fun stocksFlow(): Flow<String> = flow {
    emit("Apple")
    emit("Microsoft")

    throw Exception("Network request Failed!")
}

