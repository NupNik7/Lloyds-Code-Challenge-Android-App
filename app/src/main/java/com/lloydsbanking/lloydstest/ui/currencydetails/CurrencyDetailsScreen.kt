package com.lloydsbanking.lloydstest.ui.currencydetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lloydsbanking.lloydstest.ui.components.HeaderTextComponent
import com.lloydsbanking.lloydstest.ui.components.RegularTextComponent
import com.lloydsbanking.lloydstest.utilities.toTitleCase

@Composable
fun CurrencyDetailsScreen(
    selectedCurrencyID: String,
    selectedCurrencySymbol: String,
    selectedCurrencyType: String,
    selectedCurrencyPrice: String
) {
    Column {
        // Header Content
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
        ) {
            HeaderTextComponent("Selected Currency: ${selectedCurrencyID.toTitleCase()}")
        }
        // Selected Currency Details
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
            ) {
                RegularTextComponent(selectedCurrencyID.toTitleCase())
                RegularTextComponent(selectedCurrencySymbol)
                RegularTextComponent("Currency Type: $selectedCurrencyType")
                RegularTextComponent("Price in USD: $selectedCurrencyPrice $")
            }
        }
    }
}

@Preview()
@Composable
fun CurrencyDetailsScreenPreview() {
    CurrencyDetailsScreen(
        selectedCurrencyID = "bitcoin",
        selectedCurrencySymbol = "BTC",
        selectedCurrencyType = "crypto",
        selectedCurrencyPrice = "123500"
    )
}