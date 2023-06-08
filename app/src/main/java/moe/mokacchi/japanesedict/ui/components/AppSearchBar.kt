package moe.mokacchi.japanesedict.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    modifier: Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    onCameraIconClick: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }

    DockedSearchBar(
        modifier = modifier,
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {
            isFocused = false;
            focusManager.clearFocus()
        },
        // prevent showing the results section,
        // instead, we're handling focus changes manually.
        active = false,
        onActiveChange = {
            isFocused = it;

            if (!isFocused) {
                focusManager.clearFocus()
            }
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            IconButton(onClick = onCameraIconClick) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Search Icon")
            }
        }
    ) {}
}