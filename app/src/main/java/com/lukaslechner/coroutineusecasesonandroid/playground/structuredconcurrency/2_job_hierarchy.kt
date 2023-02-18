package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.*

// probably not a good idea to use coroutines this way.
fun main() {
    val scopeJob = Job()
    val scope = CoroutineScope(Dispatchers.Default + scopeJob)

    var passJob = Job()
    val coroutineJob = scope.launch(passJob) {

        println("Starting coroutine")
        delay(1000)
    }

    Thread.sleep(1000)

    println("passJob and coroutineJob are reference to the same job object: ${passJob == coroutineJob}")
    println("Is coroutineJob a child of scopeJob? => ${scopeJob.children.contains(coroutineJob)}")
}