package com.lloydsbanking.lloydstest.utilities

/**
 * RetryPolicy contract used to retry network requests by repository according to supplied delay.
 */
interface RetryPolicy {
    val numberOfRetries: Long
    val delayMillis: Long
    val delayFactor: Long
}

/**
 * Default RetryPolicy impl to retry network requests with exponential backoff after delay*factor
 */
data class DefaultRetryPolicy(
    override val numberOfRetries: Long = AppConstants.DEFAULT_NUMBER_OF_RETRIES,
    override val delayMillis: Long = AppConstants.DEFAULT_DELAY_MILLIS,
    override val delayFactor: Long = AppConstants.DEFAULT_DELAY_FACTOR
) : RetryPolicy