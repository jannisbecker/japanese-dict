package moe.mokacchi.japanesedict.ui.screens.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = koinViewModel(), onNavigateToCameraScreen: () -> Unit ) {
    Text(text = "Hello world")
}