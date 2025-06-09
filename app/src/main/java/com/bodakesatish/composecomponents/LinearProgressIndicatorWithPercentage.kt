package com.bodakesatish.composecomponents

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LinearProgressIndicatorWithPercentageDemo() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    // `progressDeterminate` holds the current progress for the determinate LinearProgressIndicator.
    var progressDeterminate by remember { mutableStateOf(0.0f) }
    // `isLoadingDeterminate` controls the simulation of determinate progress.
    var isLoadingDeterminate by remember { mutableStateOf(false) }

    // --- LinearProgressIndicator (Determinate) Section ---
    Text("LinearProgressIndicator (Determinate)", style = MaterialTheme.typography.titleMedium)
    if (isLoadingDeterminate) {
        // `LinearProgressIndicator` with a `progress` parameter shows a specific progress value.
        // The `progress` value should be between 0.0f and 1.0f.
        LinearProgressIndicator(
            progress = { progressDeterminate }, // Wrapped in lambda for M3
            modifier = Modifier.fillMaxWidth()
        )
    }
    Button(
        onClick = {
            if (!isLoadingDeterminate) { // Prevent multiple clicks while loading
                isLoadingDeterminate = true
                progressDeterminate = 0f // Reset progress
                coroutineScope.launch {
                    while (progressDeterminate < 1.0f && isLoadingDeterminate) { // Check isLoadingDeterminate to allow cancellation
                        delay(300) // Simulate incremental progress.
                        progressDeterminate += 0.1f
                        if (progressDeterminate > 1.0f) progressDeterminate = 1.0f // Cap at 1.0
                    }
                    if (progressDeterminate >= 1.0f) { // Only show toast if fully completed
                        Toast.makeText(context, "Download finished!", Toast.LENGTH_SHORT).show()
                    }
                    isLoadingDeterminate = false // Ensure this is set even if loop is broken
                }
            } else {
                // Optional: Allow cancelling the determinate progress
                isLoadingDeterminate = false // This will stop the while loop in the coroutine
                Toast.makeText(context, "Download cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    ) {
        Text(if (isLoadingDeterminate) "Cancel Download (${(progressDeterminate * 100).toInt()}%)" else "Start Download (Determinate)")
    }
}