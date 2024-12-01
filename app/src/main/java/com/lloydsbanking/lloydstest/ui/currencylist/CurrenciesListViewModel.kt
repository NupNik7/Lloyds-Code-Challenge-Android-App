package com.lloydsbanking.lloydstest.ui.currencylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydsbanking.lloydstest.data.repositories.CurrencyRatesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for CurrencyList screen that fetches rates and provides current UI state.
 */
@HiltViewModel
class CurrenciesListViewModel @Inject constructor(
    private val currenciesRepo: CurrencyRatesRepositoryImpl
) : ViewModel() {

    private val _currencyRatesMutableStateFlow: MutableStateFlow<CurrencyListUiState> =
        MutableStateFlow(CurrencyListUiState.Loading)
    val currencyRatesStateFlow: StateFlow<CurrencyListUiState> = _currencyRatesMutableStateFlow

    init {
        getRates()
    }

    /**
     * Fetches rates from repository; launches flow collection of currency rates in viewModelScope.
     */
    fun getRates() = viewModelScope.launch {
        _currencyRatesMutableStateFlow.value = CurrencyListUiState.Loading
        currenciesRepo.getCurrencyRates().catch { t ->
            _currencyRatesMutableStateFlow.value = CurrencyListUiState.Error(
                errorMessage = t.message
            )
        }.collect { rates ->
            _currencyRatesMutableStateFlow.value = CurrencyListUiState.Success(rates)
        }
    }
}