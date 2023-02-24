package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.androidversionrepository

import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1.utils.MainCoroutineScopeRule
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14.AndroidVersionRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class AndroidVersionRepositoryTest {

    @get:Rule
    val mainCoroutineScopeRule: MainCoroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun `loadRecentAndroidVersions() should continue to load and store android versions when calling scope gets cancelled`()
         = mainCoroutineScopeRule.runBlockingTest {

             // Arrange
             val fakeDatabase = FakeDatabase()
             val fakeApi = FakeApi()

              val repository = AndroidVersionRepository(fakeDatabase,mainCoroutineScopeRule,fakeApi)

            // Act
        val testViewModelScope = TestCoroutineScope(SupervisorJob())
        testViewModelScope.launch {
            repository.loadAndStoreRemoteAndroidVersions()
            fail("Scope should be cancelled before versions are loaded")
        }

        testViewModelScope.cancel()
        advanceUntilIdle()

            // Assert
        assertEquals(
            true, fakeDatabase.insertedIntoDb
        )
    }
}