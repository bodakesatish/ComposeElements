package com.bodakesatish.composecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleBoxDemo() {
    // Minimal Box
    Text("Minimal Box:", style = MaterialTheme.typography.titleMedium)
    Box(modifier = Modifier
        .size(200.dp, 250.dp) // Give Box a size to see stacking
        .border(1.dp, Color.Gray)
    ) {
        DemoElement("Box 1 (Bottom)", Color.Red.copy(alpha = 0.7f))
        DemoElement(
            "Box 2",
            Color.Green.copy(alpha = 0.7f),
            Modifier.align(Alignment.Center)
        ) // Needs alignment to be visible if same size
        DemoElement(
            "Box 3 (Top)",
            Color.Blue.copy(alpha = 0.7f),
            Modifier.align(Alignment.BottomEnd)
        )
    }
    Text("Default: Stacks children on top of each other, aligned to TopStart. Needs explicit alignment or different sizes for children to be distinct.", fontSize = 12.sp, modifier = Modifier.padding(bottom = 12.dp))
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
