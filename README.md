# Lloyds Banking Code Challennge Application

![lloyds_banking_short_demo](https://github.com/user-attachments/assets/57047ad3-0b90-43e3-8b30-05cbde157f9c)

Lloyds Banking Code Challenge App - This is an application that uses the CoinCap API 2.0 to provide prices(in USD) for fiat and crypto currencies in an Android application.

This application is written in Kotlin and uses an MVVM architecture with the following tech:

- Coroutines and Flow for async networking and multithreading
- Composables written in Kotlin, with some reusable components used by the CurrencyListScreen and CurrencyDetailsScreen for better readability and efficiency.
- Retrofit and OkHttp for networking
- Dagger Hilt for Dependency Injection
- Clean architecture; MVVM architecture for the Presentation layer
- In the case of IOExceptions when fetching currency data from the API, we retry upto 3 times with an expoential backoff(exponentially increasing delay between network request retries)
