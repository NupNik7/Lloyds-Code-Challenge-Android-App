package com.lloydsbanking.lloydstest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.lloydsbanking.lloydstest.data.model.CurrencyItem
import com.lloydsbanking.lloydstest.data.repositories.CurrencyRatesRepositoryImpl
import com.lloydsbanking.lloydstest.ui.currencylist.CurrenciesListViewModel
import com.lloydsbanking.lloydstest.ui.currencylist.CurrencyListUiState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Tests for CurrencyListViewModel
 */
@RunWith(MockitoJUnitRunner::class)
class CurrencyListViewModelTest {

    private lateinit var currenciesListVM: CurrenciesListViewModel

    @Mock
    private lateinit var currencyRatesRepository: CurrencyRatesRepositoryImpl

    @Rule
    @JvmField
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        currenciesListVM = CurrenciesListViewModel(currencyRatesRepository)
    }

    @Test
    fun `currenciesVM emits correct state`() = runBlockingTest {
        val expectedRates = listOf(
            CurrencyItem(
                id = "canadian-dollar",
                symbol = "CAD",
                currencySymbol = "$",
                type = "fiat",
                rateUsd = "0.7136490373944959"
            ),
            CurrencyItem(
                id = "british-pound-sterling",
                symbol = "GBP",
                currencySymbol = "£",
                type = "fiat",
                rateUsd = "1.2698493323767135"
            ),
            CurrencyItem(
                id = "bitcoin",
                symbol = "BTC",
                currencySymbol = "",
                type = "crypto",
                rateUsd = "96849.9228905385163199"
            ),
            CurrencyItem(
                id = "ethereum",
                symbol = "ETH",
                currencySymbol = "",
                type = "crypto",
                rateUsd = "3602.9484923824946879"
            ),
        )
        currenciesListVM = CurrenciesListViewModel(currencyRatesRepository)
        `when`(currencyRatesRepository.getCurrencyRates()).thenReturn(flowOf(expectedRates))
        currenciesListVM.getRates()
        currenciesListVM.currencyRatesStateFlow.test {
            val actualState = awaitItem()
            assertTrue(actualState is CurrencyListUiState.Success)
            val actualRates = (actualState as CurrencyListUiState.Success).rates
            assertEquals(expectedRates, actualRates)
        }
    }
}