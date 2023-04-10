package com.lukaslechner.coroutineusecasesonandroid.playground.flow.channels

import kotlinx.coroutines.async
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {

    // Channels are Hot
    // Channels are considered fair because they evenly distributes the values to its users.
    val channel = produce<Int> {

        println("Sending 10")
        send(10)
        println("Sending 20")
        send(20)

    }

    launch {
        channel.consumeEach { receivingValues ->
            println("Consumer1: $receivingValues")
        }
    }

    launch {
        channel.consumeEach {recievingValues ->
            println("Consumer 2: $recievingValues")
        }
    }
}