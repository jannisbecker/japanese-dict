package moe.mokacchi.japanesedict.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.mokacchi.japanesedict.ui.components.AppSearchBar
import moe.mokacchi.japanesedict.ui.components.RubyText
import moe.mokacchi.japanesedict.ui.components.TextFragment
import moe.mokacchi.japanesedict.ui.components.WordResultListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onNavigateToCameraScreen: () -> Unit
) {

    val words by viewModel.wordsResult.collectAsState()

    val textContent = listOf(
        TextFragment(text = "このルールを"),
        TextFragment(text = "守", furigana = "まも"),
        TextFragment(text = "るらない"),
        TextFragment(text = "人", furigana = "ひと"),
        TextFragment(text = "は"),
        TextFragment(text = "旅行", furigana = "りょこう"),
        TextFragment(text = "ができなくなることもあります。"),
    )

    Column {
        AppSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            query = viewModel.searchInputValue,
            onQueryChange = { viewModel.updateSearchInput(it) },
            onCameraIconClick = onNavigateToCameraScreen
        )

        words.forEach { 
            WordResultListItem(wordResult = it)
        }

        RubyText(textContent = textContent, showReadings = true)
    }

}