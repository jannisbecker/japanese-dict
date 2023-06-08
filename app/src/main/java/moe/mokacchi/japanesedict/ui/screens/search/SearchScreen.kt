package moe.mokacchi.japanesedict.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.mokacchi.japanesedict.ui.components.AppSearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onNavigateToCameraScreen: () -> Unit
) {
    AppSearchBar(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        query = viewModel.searchInputValue,
        onQueryChange = { viewModel.updateSearchInput(it) },
        onCameraIconClick = onNavigateToCameraScreen
    )
}