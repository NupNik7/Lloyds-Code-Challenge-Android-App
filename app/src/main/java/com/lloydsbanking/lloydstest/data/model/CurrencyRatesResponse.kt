package com.lloydsbanking.lloydstest.data.model

/**
 * Represents a single currency item object returned by the API
 */
data class CurrencyItem(
    val id: String,
    val symbol: String,
    val currencySymbol: String,
    val type: String,   // type of currency: fiat or crypto
    val rateUsd: String
)

/**
 * Represents response to GET request at `api.coincap.io/v2/rates/`
 */
data class CurrencyRatesResponse(
    val data: List<CurrencyItem>,
    val timestamp: String
)