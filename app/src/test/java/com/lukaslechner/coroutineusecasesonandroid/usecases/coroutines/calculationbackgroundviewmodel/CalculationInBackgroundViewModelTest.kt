package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.calculationbackgroundviewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1.utils.MainCoroutineScopeRule
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase10.CalculationInBackgroundViewModel
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3.UiState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CalculationInBackgroundViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineScopeRule: MainCoroutineScopeRule = MainCoroutineScopeRule()

    private val receivedUiStates = mutableListOf<UiState>()

    @Test
    fun `performCalculation() should perform correct calculation`() =
        mainCoroutineScopeRule.runBlockingTest {
            val viewModel = CalculationInBackgroundViewModel(mainCoroutineScopeRule.testDispatcher).apply {
              //  observe()
            }

            assertTrue(receivedUiStates.isEmpty())

            viewModel.performCalculation(1)

            assertEquals(
                UiState.Loading,
                receivedUiStates.first()
            )

//            assertEquals(
//                "1",
//                (receivedUiStates[1] as UiState.Success).result
//            )
        }
}