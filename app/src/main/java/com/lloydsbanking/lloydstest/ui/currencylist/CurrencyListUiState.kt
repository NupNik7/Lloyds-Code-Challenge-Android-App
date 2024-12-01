package com.lloydsbanking.lloydstest.ui.currencylist

import com.lloydsbanking.lloydstest.data.model.CurrencyItem

/**
 * Represents state of Currency List:
 * 1. Loading: Items are being fetched from API
 * 2. Success: Items were successfully fetched from the API
 * 3. Error: There was an error when fetching currencies from the API
 */
sealed class CurrencyListUiState {

    object Loading: CurrencyListUiState()

    class Success(val rates: List<CurrencyItem>): CurrencyListUiState()

    class Error(val errorMessage: String?): CurrencyListUiState()
}