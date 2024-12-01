package com.lloydsbanking.lloydstest.data.service

import com.lloydsbanking.lloydstest.data.model.CurrencyRatesResponse
import retrofit2.http.GET

interface CurrencyService {

    @GET("rates")
    suspend fun getCurrencyRates(): CurrencyRatesResponse
}
