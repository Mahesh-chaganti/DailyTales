package com.mahesh.storyaday.ui.presentation.uicomponents

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.cupertino.CupertinoText

@Composable
fun FormattedText(input: String) {
    val annotatedText = buildAnnotatedString {
        val regex = """\*\*(.*?)\*\*""".toRegex() // Matches **Title**
        var lastIndex = 0
        var isFirstTitle = true

        regex.findAll(input).forEach { match ->
            val title = match.groupValues[1]
            val startIndex = match.range.first
            val endIndex = match.range.last + 1

            // Append normal text before title
            append(input.substring(lastIndex, startIndex).trimEnd())

            // Add line break before the bold title
            if(!isFirstTitle){
                append("\n\n")
            }

            // Append bold title with colon
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$title:")
            }

            isFirstTitle = false
            lastIndex = endIndex
        }

        // Append any remaining text
        if (lastIndex < input.length) {
            append("\n")
            append(input.substring(lastIndex).trimStart())
        }
    }

    CupertinoText(
        text = annotatedText,
        fontSize = 16.sp,
        modifier = Modifier.padding(horizontal = 24.dp).padding(top = 18.dp,bottom = 24.dp,),
        color = if (!isSystemInDarkTheme()) Color.Black else Color.White
    )
}