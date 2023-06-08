package moe.mokacchi.japanesedict.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import moe.mokacchi.japanesedict.data.apis.JotobaApi
import moe.mokacchi.japanesedict.data.apis.WordsRequest

class SearchViewModel(
    jotobaApi: JotobaApi
) : ViewModel() {

    var searchInputValue by mutableStateOf("")
        private set

    @OptIn(ExperimentalCoroutinesApi::class)
    val wordsResult = snapshotFlow { searchInputValue }
        .debounce(1000)
        .filter { it.isNotEmpty() }
        .mapLatest { jotobaApi.searchWords(WordsRequest(it))}
        .mapLatest { it.words }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    fun updateSearchInput(newInput: String) {
        searchInputValue = newInput
    }

}