package com.bodakesatish.composecomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bodakesatish.composecomponents.ui.theme.ComposeComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeComponentsTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    // LocalContext provides access to the Android Context, used here for showing Toasts.
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize() // Takes full screen space.
                .padding(innerPadding) // Applies Scaffold's padding.
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp + 72.dp
                ) // Added extra bottom padding
                .verticalScroll(rememberScrollState()), // Makes the content scrollable.
            horizontalAlignment = Alignment.CenterHorizontally, // Centers children horizontally.
            verticalArrangement = Arrangement.spacedBy(12.dp) // Adds space between children.
        ) {
            val context = LocalContext.current
            OutlinedCardDemo(context)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeComponentsTheme {
        MainContent()
    }
}