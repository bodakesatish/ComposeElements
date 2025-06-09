package com.bodakesatish.composecomponents

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularProgressIndicatorDemo() {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // --- State for Progress Indicators ---
    // `isLoadingCircular` controls the visibility of the CircularProgressIndicator.
    var isLoadingCircular by remember { mutableStateOf(false) }

    // --- CircularProgressIndicator Section ---
    Text("CircularProgressIndicator Demo", style = MaterialTheme.typography.titleMedium)
    if (isLoadingCircular) {
        // `CircularProgressIndicator` displays an indeterminate circular loading animation.
        // It's used when the progress duration is unknown.
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            // color = MaterialTheme.colorScheme.secondary, // Optional: Custom color
            // strokeWidth = 5.dp // Optional: Custom stroke width
        )
    } else {
        Button(onClick = {
            isLoadingCircular = true
            coroutineScope.launch {
                delay(13000) // Simulate a network request or long task.
                isLoadingCircular = false
                Toast.makeText(context, "Circular loading finished!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Load with Circular")
        }
    }
}