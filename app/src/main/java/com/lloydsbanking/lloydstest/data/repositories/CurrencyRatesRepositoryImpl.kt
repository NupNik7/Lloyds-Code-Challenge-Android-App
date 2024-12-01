package com.lloydsbanking.lloydstest.data.repositories

import com.lloydsbanking.lloydstest.utilities.RetryPolicy
import com.lloydsbanking.lloydstest.data.service.CurrencyService
import com.lloydsbanking.lloydstest.data.model.CurrencyItem
import com.lloydsbanking.lloydstest.utilities.retryWithPolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository that calls the getCurrencyRates from the API and emits a flow of the result
 */
class CurrencyRatesRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService,
    private val retryPolicy: RetryPolicy
) : CurrencyRatesRepository {

    override suspend fun getCurrencyRates(): Flow<List<CurrencyItem>> = flow {
        emit(currencyService.getCurrencyRates().data)
    }.retryWithPolicy(retryPolicy).flowOn(Dispatchers.IO)

}