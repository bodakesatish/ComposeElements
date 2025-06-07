package com.bodakesatish.composecomponents.buttons

import android.content.Context
import android.widget.Toast
import android.widget.ToggleButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonTypeStandard(name: String, content: Context) {
    Button(onClick = {
        Toast.makeText(content, "Button Clicked", Toast.LENGTH_SHORT).show()
    }) {
        Text(name)
    }
}

// Demonstrates different types of Material 3 Buttons.
@Composable
fun ButtonTypeFilledWithIcon(context: Context) {
    // Filled Button (default): High emphasis, for primary actions.
    Button(onClick = {
        Toast.makeText(context, "Filled Button Clicked", Toast.LENGTH_SHORT).show()
    }) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing)) // Standard spacing between icon and text.
        Text("Filled Button")
    }
}

@Composable
fun ButtonTypeFilledTonalButton(context: Context) {
    // FilledTonalButton: Medium emphasis, a softer alternative to FilledButton.
    // Good for actions like "Next" in a wizard or secondary prominent actions.
    FilledTonalButton(onClick = { Toast.makeText(context, "Filled Tonal Button Clicked", Toast.LENGTH_SHORT).show() }) {
        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart", modifier = Modifier.size(ButtonDefaults.IconSize))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Filled Tonal")
    }
}

@Composable
fun OutlinedButtonContent(context: Context) {
    // OutlinedButton: Medium emphasis, for secondary actions that need more emphasis than TextButton.
    OutlinedButton(onClick = { Toast.makeText(context, "Outlined Button Clicked", Toast.LENGTH_SHORT).show() }) {
        Icon(Icons.Filled.Call, contentDescription = "Call", modifier = Modifier.size(ButtonDefaults.IconSize))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Outlined Button")
    }
}

@Composable
fun ElevatedButtonContent(context: Context) {
    // ElevatedButton: Adds a shadow, for actions that need to stand out slightly from a flat layout.
    ElevatedButton(onClick = { Toast.makeText(context, "Elevated Button Clicked", Toast.LENGTH_SHORT).show() }) {
        Icon(Icons.Filled.Add, contentDescription = "Add", modifier = Modifier.size(ButtonDefaults.IconSize))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Elevated Button")
    }
}

@Composable
fun TextButtonContent(context: Context) {
    TextButton(onClick = { Toast.makeText(context, "Text Button Clicked", Toast.LENGTH_SHORT).show() }) {
        Text("Text Button")
    }
}

@Composable
fun IconButtonContent(context: Context) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { // Arrange IconButtons in a row.
        // Standard IconButton.
        IconButton(onClick = { Toast.makeText(context, "Menu Icon Clicked", Toast.LENGTH_SHORT).show() }) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }

        // Disabled IconButton.
        IconButton(onClick = { /* Will not be called */ }, enabled = false) {
            Icon(Icons.Filled.Lock, contentDescription = "Disabled Lock")
        }

        // IconButton with custom colors (example).
        // Note: For proper theming, it's better to customize colors via the ButtonColors parameter in the theme.
        IconButton(
            onClick = { Toast.makeText(context, "Favorite Icon Clicked", Toast.LENGTH_SHORT).show() },
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary, // Color of the icon itself.
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite with custom color")
        }
    }
}

@Composable
fun FabTypesContent(context: Context) {
    // Standard FloatingActionButton.
    FloatingActionButton(
        onClick = { Toast.makeText(context, "FAB Clicked", Toast.LENGTH_SHORT).show() },
        containerColor = MaterialTheme.colorScheme.secondaryContainer // Example of custom container color.
    ) {
        Icon(Icons.Filled.Edit, "Edit FAB")
    }

    // SmallFloatingActionButton: For less prominent actions or tight spaces.
    SmallFloatingActionButton(
        onClick = { Toast.makeText(context, "Small FAB Clicked", Toast.LENGTH_SHORT).show() }
    ) {
        Icon(Icons.Filled.Add, "Small Add FAB")
    }

    // LargeFloatingActionButton: For very prominent actions.
    LargeFloatingActionButton(
        onClick = { Toast.makeText(context, "Large FAB Clicked", Toast.LENGTH_SHORT).show() },
        shape = CircleShape // Default shape, but can be customized.
    ) {
        Icon(Icons.Filled.ShoppingCart, "Large Cart FAB", modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize))
    }

    // ExtendedFloatingActionButton (can also be used inline).
    // Shows both an icon and text.
    ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Call, "Call Icon") },
        text = { Text("Call Support") },
        onClick = { Toast.makeText(context, "Extended FAB (inline) Clicked", Toast.LENGTH_SHORT).show() }
    )
}

@Composable
fun ToggleButtonContent(context: Context) {
    var isButtonEnabled by remember { mutableStateOf(true) } // State to toggle button enabled status.

    // Button with enabled state controlled by a variable.
    Button(
        onClick = { Toast.makeText(context, "Toggled Button Clicked", Toast.LENGTH_SHORT).show() },
        enabled = isButtonEnabled // Dynamically sets the enabled state.
    ) {
        Text(if (isButtonEnabled) "Enabled Button" else "Disabled Button")
    }

    Button(
        onClick = { Toast.makeText(context, "Toggled Button Clicked", Toast.LENGTH_SHORT).show() },
        enabled = false // Dynamically sets the enabled state.
    ) {
        Text(if (false) "Enabled Button" else "Disabled Button")
    }

    // Button to toggle the enabled state of the button above.
    TextButton(onClick = { isButtonEnabled = !isButtonEnabled }) {
        Text(if (isButtonEnabled) "Disable Above Button" else "Enable Above Button")
    }

}

@Composable
fun ButtonPaddingContent(context: Context) {
    Button(
        onClick = { Toast.makeText(context, "Custom Padding Button", Toast.LENGTH_SHORT).show() },
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp) // Larger padding.
    ) {
        Text("Custom Padding")
    }
}

@Composable
fun ButtonCustomShape(context: Context) {
    // Button with a custom shape.
    Button(
        onClick = { Toast.makeText(context, "Custom Shape Button", Toast.LENGTH_SHORT).show() },
        shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp) // Example of a cut corner shape.
    ) {
        Text("Custom Shape")
    }
}

@Composable
fun ButtonColorsContent(context: Context) {
    Button(
        onClick = { Toast.makeText(context, "Custom Colors Button", Toast.LENGTH_SHORT).show() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta, // Custom background/container color.
            contentColor = Color.White,     // Custom text/icon color.
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        )
    ) {
        Text("Custom Colors")
    }
}