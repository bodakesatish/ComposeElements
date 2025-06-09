package com.bodakesatish.composecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- 1. Minimal/Default Code ---
@Composable
fun MinimalRowLayoutsDemo() {
    // Minimal Row
    Text("Minimal Row:", style = MaterialTheme.typography.titleMedium)
    Row(modifier = Modifier.border(1.dp, Color.Gray)) {
        DemoElement("Row 1", Color.LightGray)
        DemoElement("Row 2", Color.Green.copy(alpha = 0.5f))
        DemoElement("Row 3", Color.Blue.copy(alpha = 0.5f))
    }
    Text("Default: Arranges horizontally, children take their own height, aligned to Top.", fontSize = 12.sp, modifier = Modifier.padding(bottom = 12.dp))

    Spacer(modifier = Modifier.height(16.dp))
}

// --- Helper Composable for consistent item styling ---
// (Assuming MinimalLayoutsDemo and AdvancedLayoutsDemo are in the same file or accessible)00
@Composable
fun DemoElement(text: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = modifier
            .background(color)
            .padding(16.dp)
    )
}