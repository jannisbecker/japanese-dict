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
import kotlinx.coroutines.flow.forEach
import moe.mokacchi.japanesedict.ui.components.AppSearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onNavigateToCameraScreen: () -> Unit
) {

    val words by viewModel.wordsResult.collectAsState()

    AppSearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        query = viewModel.searchInputValue,
        onQueryChange = { viewModel.updateSearchInput(it) },
        onCameraIconClick = onNavigateToCameraScreen
    )
    Column {
        words.forEach { word ->
            Text(text=word.reading.kanji+"")
        }
    }
}