package com.lloydsbanking.lloydstest.utilities

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import java.util.Locale

/**
 * Flow extension function that retries according to the supplied retry policy.
 * If an IOException occurs during the network call, we retry after delay = currentDelay*Factor
 */
fun <T> Flow<T>.retryWithPolicy(
    retryPolicy: RetryPolicy
): Flow<T> {
    var currentDelay = retryPolicy.delayMillis
    val delayFactor = retryPolicy.delayFactor
    return retryWhen { cause, attempt ->
        if (cause is IOException && attempt < retryPolicy.numberOfRetries) {
            delay(currentDelay)
            currentDelay *= delayFactor
            return@retryWhen true
        } else {
            return@retryWhen false
        }
    }
}

/**
 * String extension function to convert String currency IDs to title case.
 */
fun String.toTitleCase(): String =
    replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
        else it.toString()
    }