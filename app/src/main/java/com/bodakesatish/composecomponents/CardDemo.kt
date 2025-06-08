package com.bodakesatish.composecomponents

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedCardDemo(context: android.content.Context) {
    ElevatedCard(
        // `onClick` can be added to make the entire card clickable.
        onClick = { Toast.makeText(context, "Elevated Card clicked!", Toast.LENGTH_SHORT).show() },
        modifier = Modifier
            .fillMaxWidth(),
         elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Padding inside the card
                .fillMaxWidth()
        ) {
            Text(
                text = "Item Title",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "This is some supporting text for the item. It can span multiple lines and provide more details.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Example of an image placeholder within a card.
            ImagePlaceholder()
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { Toast.makeText(context, "Action 1", Toast.LENGTH_SHORT).show() }) {
                    Text("ACTION 1")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { Toast.makeText(context, "Action 2", Toast.LENGTH_SHORT).show() }) {
                    Text("ACTION 2")
                }
            }
        }
    }
}

// Simple placeholder for an image within a card.
@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp)) // Rounded corners for the placeholder
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
//        Text(
//            "Image Placeholder",
//            color = MaterialTheme.colorScheme.onSecondaryContainer,
//            style = MaterialTheme.typography.bodyLarge
//        )
//         In a real app, you'd use:
         Image(
             painter = painterResource(id = R.drawable.ic_home_144),
             contentDescription = "Descriptive text",
             contentScale = ContentScale.FillHeight,
             modifier = Modifier.fillMaxSize()
         )
    }
}