package moe.mokacchi.japanesedict.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

data class TextFragment(
    val text: String,
    val furigana: String? = null,
)

@Composable
fun RubyText(
    textContent: List<TextFragment>,
    showReadings: Boolean = false,
) {
    val (text, inlineContent) = remember(textContent) {
        calculateAnnotatedString(textContent = textContent, showReadings = showReadings)
    }

    Text(text = text, inlineContent = inlineContent)
}

fun calculateAnnotatedString(textContent: List<TextFragment>, showReadings: Boolean):
        Pair<AnnotatedString, Map<String, InlineTextContent>> {
    val inlineContent = mutableMapOf<String, InlineTextContent>()

    return buildAnnotatedString {
        for (elem in textContent) {
            val text = elem.text
            val reading = elem.furigana

            // If there is not reading available, simply add the text and move to the next element.
            if (reading == null) {
                append(text)
                continue
            }

            // Words larger than one character/kanji need a small amount of additional space in their
            // x-dimension.
            val width = (text.length.toDouble() + (text.length - 1) * 0.05).em
            appendInlineContent(text, text)
            inlineContent[text] = InlineTextContent(
                // TODO: find out why height and width need magic numbers.
                placeholder = Placeholder(
                    width = width,
                    height = 1.97.em,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Bottom,
                ),
                children = {
                    val readingFontSize = LocalTextStyle.current.fontSize / 2
                    val boxHeight = with(LocalDensity.current) { readingFontSize.toDp() }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        Box(modifier = Modifier.requiredHeight(boxHeight + 3.dp)) {
                            if (showReadings) {
                                Text(
                                    modifier = Modifier.wrapContentWidth(unbounded = true),
                                    text = reading,
                                    style = TextStyle.Default.copy(fontSize = readingFontSize)
                                )
                            }
                        }
                        Text(text = text)
                    }
                }
            )
        }
    } to inlineContent
}

@Preview()
@Composable
fun RubyTextPreview() {

    val fragments = listOf(
        TextFragment(text = "東京", furigana = "とうきょう"),
        TextFragment(text = "は"),
        TextFragment(text = "最高", furigana = "さいこう"),
    )

    RubyText(fragments)
}

@Preview()
@Composable
fun RubyTextPreviewNoFurigana() {

    val fragments = listOf(
        TextFragment(text = "とうきょう"),
        TextFragment(text = "は"),
        TextFragment(text = "さいこう"),
    )

    RubyText(fragments)
}

@Preview(widthDp = 60, heightDp = 200)
@Composable
fun RubyTextPreviewNewLine() {

    val fragments = listOf(
        TextFragment(text = "東京", furigana = "とうきょう"),
        TextFragment(text = "は"),
        TextFragment(text = "最高", furigana = "さいこう"),
        TextFragment(text = "ですはね"),
    )

    RubyText(fragments)
}