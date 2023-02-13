package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlin.concurrent.thread

fun main() {
    println("main starts")
    threadRoutines(1, 500)
    threadRoutines(2,300)
    println("main ends" )
}

fun threadRoutines(number: Int, delay: Long) {
    thread {
        println("Routine $number starts work")
        Thread.sleep(delay)
        println("Routine $number has finished")
    }
}