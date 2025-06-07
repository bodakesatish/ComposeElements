package com.bodakesatish.composecomponents.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

// --- Text Composable Examples ---
// This section demonstrates various parameters of the Text composable.
@Composable
fun TextDemoSectionOne() {

    // Simplest Text composable with default styling.
    Text("1.Default Text")

    // Text with a specific color.
    Text(
        text = "2.Colored Text",
        color = MaterialTheme.colorScheme.primary // Uses the primary color from the current Material Theme.
    )

    // Text with a specific font size.
    Text(
        text = "3.Sized Text (24.sp)",
        fontSize = 24.sp // Sets the font size to 24 scale-independent pixels.
    )

    // Text with bold font weight.
    Text(
        text = "4.Bold Text",
        fontWeight = FontWeight.Bold // Makes the text bold.
    )

    // Text with italic font style.
    Text(
        text = "5.Italic Text",
        fontStyle = FontStyle.Italic // Makes the text italic.
    )

    // Text with a monospace font family.
    Text(
        text = "6.Monospace Font",
        fontFamily = FontFamily.Monospace // Uses a generic monospace font.
    )

    // Text with an underline decoration.
    Text(
        text = "7.Underlined Text",
        textDecoration = TextDecoration.Underline
    )

    // Text with a line-through decoration.
    Text(
        text = "8.LineThrough Text",
        textDecoration = TextDecoration.LineThrough
    )

    // Text aligned to the center.
    // For TextAlign.Center to be effective, the Text composable needs to occupy more space than its content,
    // often achieved by `Modifier.fillMaxWidth()` or a fixed width.
    Text(
        text = "9.Centered Text (takes full width of its direct parent here)",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth() // Makes the Text composable take the full width of its parent.
            .background(Color.LightGray.copy(alpha = 0.3f)) // Background to visualize the Text's bounds.
    )
}

@Composable
fun TextDemoSectionTwo() {
    // Text with justified alignment.
    // Justification distributes space in the line so that it aligns to both left and right edges.
    Text(
        text = "10.Justified Text (needs multiple lines and enough width to show effect). Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        textAlign = TextAlign.Justify,
        modifier = Modifier.background(Color.Yellow.copy(alpha = 0.2f))
    )

    // Text with increased letter spacing.
    Text(
        text = "11.Increased Letter Spacing",
        letterSpacing = 0.2.em // `em` is a unit relative to the current font size. 0.2em means 20% of the font size.
    )

    // Text with custom line height (space between lines of text).
    Text(
        text = "12.Custom Line Height. This text will have more space between lines. Ideal for readability in longer paragraphs.",
        lineHeight = 1.8.em // Can also use .sp, e.g., 30.sp. 1.8em means the line height is 1.8 times the font size.
    )

    // Text handling overflow with an ellipsis (...).
    // `maxLines = 1` restricts the text to a single line.
    // `overflow = TextOverflow.Ellipsis` adds "..." if the text exceeds `maxLines`.
    Text(
        text = "13.Overflow Ellipsis: This is a very long text that will not fit in one line and should be truncated with an ellipsis at the end.",
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = Modifier.width(200.dp) // Constrain width to demonstrate overflow.
    )

    // Text handling overflow by clipping the content.
    // `softWrap = false` prevents text from wrapping to the next line.
    // `overflow = TextOverflow.Clip` cuts off text that doesn't fit.
    Text(
        text = "14.Overflow Clip: This is a very long text that will be clipped if it doesn't fit.",
        overflow = TextOverflow.Clip,
        softWrap = false,
        maxLines = 1,
        modifier = Modifier.width(200.dp) // Constrain width.
    )

    // Text handling overflow by making it visible beyond its bounds.
    // `overflow = TextOverflow.Visible` allows text to draw outside its measured bounds if the parent allows it.
    // The actual behavior can depend on parent constraints and clipping.
    Text(
        text = "15.Overflow Visible: This very long text might extend beyond its bounds if the parent allows it.",
        overflow = TextOverflow.Visible,
        maxLines = 1,
        modifier = Modifier
            .width(150.dp) // Constrain width.
            .border(1.dp, Color.Red) // Border to visualize the Text's defined bounds.
    )

    // Text styled using a predefined typography style from the Material Theme.
    Text(
        text = "16.Using MaterialTheme Typography (Headline Small)",
        style = MaterialTheme.typography.headlineSmall // Applies font size, weight, etc., from this theme style.
    )

    // Text with mixed styles using `buildAnnotatedString`.
    // `buildAnnotatedString` allows applying different styles (SpanStyle) to different parts of the text.
    Text(
        text = buildAnnotatedString {
            append("17.This is ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append("styled")
            }
            append(" text using ")
            withStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("AnnotatedString")
            }
            append(".")
        }
    )
}