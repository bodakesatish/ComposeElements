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
fun LinearProgressIndicatorDemo() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    // `isLoadingLinear` controls the visibility of the LinearProgressIndicator (indeterminate).
    var isLoadingLinear by remember { mutableStateOf(false) }

    Text("LinearProgressIndicator (Indeterminate)", style = MaterialTheme.typography.titleMedium)
    if (isLoadingLinear) {
        // `LinearProgressIndicator` (without a `progress` parameter) displays an indeterminate linear animation.
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
            // color = MaterialTheme.colorScheme.tertiary, // Optional: Custom color
            // trackColor = MaterialTheme.colorScheme.surfaceVariant, // Optional: Custom track color
        )
    } else {
        Button(onClick = {
            isLoadingLinear = true
            coroutineScope.launch {
                delay(3000) // Simulate a task.
                isLoadingLinear = false
                Toast.makeText(context, "Linear loading finished!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Load with Linear (Indeterminate)")
        }
    }
}