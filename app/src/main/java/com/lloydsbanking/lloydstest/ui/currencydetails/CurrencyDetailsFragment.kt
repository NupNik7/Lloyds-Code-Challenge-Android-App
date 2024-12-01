package com.lloydsbanking.lloydstest.ui.currencydetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.lloydsbanking.lloydstest.ui.theme.CodeChallengeAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Currency Details Fragment - Displays details(such as price) for currency selected by the user.
 */
@AndroidEntryPoint
class CurrencyDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            arguments?.let {
                val selectedCurrencyID = it.getString("currency_id")
                val selectedCurrencySymbol = it.getString("currency_symbol")
                val selectedCurrencyType = it.getString("currency_type")
                val selectedCurrencyPrice = it.getString("currency_price")
                setContent {
                    CodeChallengeAppTheme {
                        CurrencyDetailsScreen(
                            selectedCurrencyID ?: "Error fetching currency ID",
                            selectedCurrencySymbol ?: "Error fetching currency Symbol",
                            selectedCurrencyType ?: "Error fetching currency Type",
                            selectedCurrencyPrice ?: "Error fetching currency Price"
                        )
                    }
                }
            }
        }
    }
}