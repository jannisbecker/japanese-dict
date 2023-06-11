package moe.mokacchi.japanesedict.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import moe.mokacchi.japanesedict.data.apis.Word

@Composable
fun WordResultListItem(wordResult: Word) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Column() {
            Text(text = wordResult.reading.kana)
            if (wordResult.common) {
                Text(text = "common word")
            }
        }

        Column() {
            wordResult.senses.forEach {
                Column() {
                    Text(text = it.glosses.joinToString(separator = "; "))
                }
            }
        }
    }
}