package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.playground

import kotlinx.coroutines.*
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SystemUnderTest {

    suspend fun functionWithDelay(): Int {
        delay(1000)
        return 42
    }
}

fun CoroutineScope.functionThatStartsNewCoroutine() {
    launch {
        delay(1000)
        println("Coroutine completed!")
    }
}

class TestClass {

    @Test
    fun `functionWithDelay() should return 42`() = runBlockingTest {

        val realTimeStart = System.currentTimeMillis()
        val virtualTimeStart = currentTime

        functionThatStartsNewCoroutine()

        advanceTimeBy(1000)

1
        val sut = SystemUnderTest()

        val actual = sut.functionWithDelay()
        assertEquals(42,actual)

        val realTimeDuration = System.currentTimeMillis() - realTimeStart
        val virtualTimeDuration = currentTime - virtualTimeStart

        println("Test took $realTimeDuration real ms")
        println("Test took $virtualTimeDuration virtual ms")
    }
}

/**
 * runblockingtest uses virtual time
 * runblocking uses real time
 *
 * runblockingtest is faster
 * */