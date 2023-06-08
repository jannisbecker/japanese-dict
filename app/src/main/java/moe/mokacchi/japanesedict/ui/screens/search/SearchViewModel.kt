package moe.mokacchi.japanesedict.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    var searchInputValue by mutableStateOf("")
        private set

    fun updateSearchInput(newInput: String) {
        searchInputValue = newInput
    }

}