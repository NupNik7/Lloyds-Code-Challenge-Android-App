package com.lloydsbanking.lloydstest.ui.currencylist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lloydsbanking.lloydstest.data.model.CurrencyItem
import com.lloydsbanking.lloydstest.ui.components.RegularTextComponent

/**
 * Compose screen of list of currencies provided by CurrenciesListViewModel.
 */
@Composable
fun CurrencyListScreen(
    currenciesListVM: CurrenciesListViewModel,
    onCurrencyClickListener: (CurrencyItem) -> Unit
) {
    val state by currenciesListVM.currencyRatesStateFlow.collectAsState()
    CurrencyScreenContent(
        state,
        onCurrencyClickListener
    )
}

@Composable
fun CurrencyScreenContent(
    state: CurrencyListUiState,
    onCurrencyClickListener: (CurrencyItem) -> Unit
) {
    when (state) {
        is CurrencyListUiState.Loading -> {
            LoadingView()
        }

        is CurrencyListUiState.Success -> {
            ListOfCurrencies(state.rates, onCurrencyClickListener)
        }

        is CurrencyListUiState.Error -> {
            ErrorView(state.errorMessage)
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ListOfCurrencies(
    currencies: List<CurrencyItem>,
    onCurrencyClickListener: (CurrencyItem) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.testTag("123")  // TODO: testing, write test to check tag
    ) {
        items(
            items = currencies,
            key = { currencyItem ->
                currencyItem.id
            },
        ) { currencyItem ->
            CurrencyCard(
                item = currencyItem,
                onCurrencyClickListener = onCurrencyClickListener
            )
        }
    }
}

/**
 * Custom Composable Card for each currency item
 */
@Composable
fun CurrencyCard(
    item: CurrencyItem,
    onCurrencyClickListener: (CurrencyItem) -> Unit
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCurrencyClickListener.invoke(item)
            }
    ) {
        RegularTextComponent(textValue = item.id)
        RegularTextComponent(textValue = item.symbol)
    }
}

// Composable rendered for error state
@Composable
fun ErrorView(errorMessage: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .fillMaxSize(), // TODO: remove comment - else dialog will stay top start centered
    ) {
        RegularTextComponent(errorMessage ?: "Error fetching latest currency rates")
    }
}

// Successful Content Preview
@Preview(showBackground = true)
@Composable
fun SuccessPreview() {
    CurrencyScreenContent(
        CurrencyListUiState.Success(
            rates = listOf(
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
        )
    ) { selectedCurrencyItem ->
        println("Currency Clicked: ${selectedCurrencyItem.id}")
    }
}

// Error Content Preview
@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    CurrencyScreenContent(
        CurrencyListUiState.Error("There was an error when fetching currencies")
    ) { selectedCurrencyItem ->
        println("Currency Clicked: ${selectedCurrencyItem.id}")
    }
}