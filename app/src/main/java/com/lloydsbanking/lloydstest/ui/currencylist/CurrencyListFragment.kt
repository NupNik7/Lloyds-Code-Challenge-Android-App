package com.lloydsbanking.lloydstest.ui.currencylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lloydsbanking.lloydstest.R
import com.lloydsbanking.lloydstest.data.model.CurrencyItem
import com.lloydsbanking.lloydstest.ui.theme.CodeChallengeAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Currency List Fragment - Displays a list of  currencies fetched from the API.
 */
@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val currenciesListVM: CurrenciesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CodeChallengeAppTheme {
                    CurrencyListScreen(currenciesListVM) { currency ->
                        val selectedCurrencyBundle = buildBundleForSelectedCurrency(currency)
                        findNavController().navigate(
                            R.id.action_FirstFragment_to_SecondFragment,
                            selectedCurrencyBundle
                        )
                    }
                }
            }
        }
    }

    /**
     * Builds a bundle of data corresponding to the selected currency for CurrencyDetails UI
     */
    private fun buildBundleForSelectedCurrency(selectedCurrency: CurrencyItem) : Bundle =
        Bundle().apply {
            putString("currency_id", selectedCurrency.id)
            putString("currency_symbol", selectedCurrency.symbol)
            putString("currency_type", selectedCurrency.type)
            putString("currency_price", selectedCurrency.rateUsd)
        }
}
