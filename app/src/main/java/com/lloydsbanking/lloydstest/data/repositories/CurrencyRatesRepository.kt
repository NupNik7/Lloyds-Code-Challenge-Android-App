package com.lloydsbanking.lloydstest.data.repositories

import com.lloydsbanking.lloydstest.data.model.CurrencyItem
import kotlinx.coroutines.flow.Flow

/**
 *
 */
interface CurrencyRatesRepository {
    suspend fun getCurrencyRates(): Flow<List<CurrencyItem>>
}
