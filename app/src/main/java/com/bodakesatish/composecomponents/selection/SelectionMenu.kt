package com.bodakesatish.composecomponents.selection

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuDemo(context: android.content.Context) {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
    var selectedText by remember { mutableStateOf(suggestions[0]) }

    Text("Exposed Dropdown Menu (Spinner Style):", style = MaterialTheme.typography.titleMedium)
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { /* Usually read-only or type to filter */ },
            readOnly = true,
            label = { Text("Fruit") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor() // This is important!
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedText = label
                        expanded = false
                        Toast.makeText(context, "Selected: $label", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Composable
fun CheckboxDemo(context: android.content.Context) {
    var checkedState by remember { mutableStateOf(true) }
    var triState by remember { mutableStateOf(androidx.compose.ui.state.ToggleableState.Indeterminate) }

    Text("Checkboxes:", style = MaterialTheme.typography.titleMedium)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.fillMaxWidth() // REMOVED - Row will now wrap its content//It Makes the whole row take width
            .toggleable(
                value = checkedState,
                onValueChange = {
                    checkedState = it
                    Toast
                        .makeText(context, "Checkbox: $it", Toast.LENGTH_SHORT)
                        .show()
                },
                role = Role.Checkbox // Important for accessibility
            )
            .padding(vertical = 8.dp) // Add some padding for better tap target
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = null // Handled by toggleable modifier on Row
//            onCheckedChange = {
//                checkedState = it
//                Toast.makeText(context, "Checkbox: $it", Toast.LENGTH_SHORT).show()
//            }
        )
        Text(
            text = "Simple Checkbox",
            modifier = Modifier.padding(start = 8.dp) // Padding between checkbox and text
        )
    }

    // Tri-State Checkbox Row - Wrap Content
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            //.fillMaxWidth()
//            .toggleable(
//                value = triState == ToggleableState.On, // Or map Indeterminate as you wish for value
//                onValueChange = { /* This onValueChange is for boolean, might need adjustment for TriState logic */
//                    // For TriState, directly calling onClick might be better, or map boolean to TriState
//                    // For simplicity, let's keep the TriStateCheckbox's onClick and just make Row clickable
//                    // Option 1: Keep existing TriStateCheckbox onClick and use a simple clickable for Row
//                },
//                role = Role.Checkbox // This makes the Row behave like a checkbox for accessibility
//                // For direct TriState control with toggleable, it's more complex.
//                // It's often simpler to use Modifier.clickable on the Row and call the TriStateCheckbox's existing onClick lambda.
//                // So, let's use Modifier.clickable for the TriState example for clarity with its existing logic.
//            )
            // Using Modifier.clickable for TriState to directly use its existing toggle logic
            // .clickable { // Alternative using simple clickable - see Option 2 below for TriState
            //    // Call the same logic as TriStateCheckbox's onClick
            // }
            .clickable( // Using clickable for simpler TriState logic handling from Row
                onClick = {
                    val newState = when (triState) {
                        ToggleableState.On -> ToggleableState.Off
                        ToggleableState.Off -> ToggleableState.Indeterminate
                        ToggleableState.Indeterminate -> ToggleableState.On
                    }
                    triState = newState
                    Toast
                        .makeText(context, "TriStateCheckbox: $newState", Toast.LENGTH_SHORT)
                        .show()
                },
                role = Role.Checkbox,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    radius = Dp.Unspecified, // Or specific Dp value
                    color = Color.Unspecified // Or specific Color
                ) // NEW WAY
            )
            .padding(vertical = 8.dp, horizontal = 4.dp) // Added small horizontal padding
    ) {
        TriStateCheckbox(
            state = triState,
            onClick = null // Handled by clickable Row
        )
        Text(
            text = "Tri-State Checkbox",
            modifier = Modifier.padding(start = 8.dp)
        )
//        val onTriStateClick = {
//            val newState = when (triState) {
//                ToggleableState.On -> ToggleableState.Off
//                ToggleableState.Off -> ToggleableState.Indeterminate
//                ToggleableState.Indeterminate -> ToggleableState.On
//            }
//            triState = newState
//            Toast.makeText(context, "TriStateCheckbox: $newState", Toast.LENGTH_SHORT).show()
//        }
//        // Applying toggleable to the Row is still good for accessibility role.
//        // The onValueChange of toggleable expects a (Boolean) -> Unit.
//        // We can adapt or use the TriStateCheckbox's own onClick.
//        // Let's modify the Row to use the existing onTriStateClick for its toggle action.
//        // This Row is now also clickable and will trigger the TriStateCheckbox logic.
//        // We set onCheckedChange of TriStateCheckbox to null as the Row handles the click.
//
//        TriStateCheckbox(
//            state = triState,
//            onClick = null, // Logic is now primarily on the Row or called by the Row
//            modifier = Modifier.noRippleClickable { onTriStateClick() } // Make checkbox itself clickable without ripple if row has ripple
//        )
//        Text(
//            text = "Tri-State Checkbox",
//            modifier = Modifier
//                .padding(start = 8.dp)
//                .noRippleClickable { onTriStateClick() } // Make text also clickable without double ripple
//        )
    }
}


/**
 * A custom Modifier that makes a composable clickable without showing a visual ripple effect.
 *
 * This is useful when the parent composable already provides a click indication (like ripple)
 * and you want to avoid a double indication on the child.
 *
 * @param onClick The lambda to be executed when the composable is clicked.
 */
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null, // This is the key: setting indication to null removes the ripple
        onClick = { onClick() }
    )
}

@Composable
fun RadioButtonDemo(context: android.content.Context) {
    val radioOptions = listOf("Option A", "Option B", "Option C")
    // var (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }
    Text("Radio Buttons:", style = MaterialTheme.typography.titleMedium)
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    //.fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            selectedOption = text // Update state directly here
                            Toast
                                .makeText(context, "$text selected", Toast.LENGTH_SHORT)
                                .show()
                        },
                        role = Role.RadioButton // Important for accessibility
                    )
                    .padding(vertical = 8.dp), // Increased padding for better tap target
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
//                    onClick = {
//                        onOptionSelected(text)
//                        Toast.makeText(context, "$text selected", Toast.LENGTH_SHORT).show()
//                    }
                    onClick = null // Handled by the Row's selectable modifier
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp)// Padding between radio and text
                )
            }
        }
    }
}

@Composable
fun SwitchDemo(context: android.content.Context) {
    var simpleSwitchChecked by remember { mutableStateOf(false) }
    var disabledSwitchChecked by remember { mutableStateOf(true) } // Example initial state for disabled
    var isSwitchEnabled by remember { mutableStateOf(true) } // To control enabled state of the first switch

    Text("Switches:", style = MaterialTheme.typography.titleMedium)

    // Example 1: Standard Switch (that can be programmatically disabled/enabled)
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                // .fillMaxWidth() // Optional: uncomment if you want the row to span full width
                .toggleable(
                    value = simpleSwitchChecked,
                    onValueChange = {
                        if (isSwitchEnabled) { // Only change if enabled
                            simpleSwitchChecked = it
                            Toast.makeText(context, "Switch: $it", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Switch is disabled", Toast.LENGTH_SHORT).show()
                        }
                    },
                    role = Role.Switch, // Important for accessibility
                    enabled = isSwitchEnabled // Pass the enabled state to toggleable
                )
                .padding(vertical = 8.dp, horizontal = 4.dp) // Padding for better tap target
        ) {
            // The Text should ideally come before the Switch for typical LTR layout ordering
            // if the whole row is clickable and associated with the Switch's state.
            Text(
                text = "Interactive Switch",
                modifier = Modifier.padding(end = 16.dp) // Padding between text and switch
            )
            Switch(
                checked = simpleSwitchChecked,
                onCheckedChange = null, // Handled by Row's toggleable
                enabled = isSwitchEnabled, // Control enabled state here
                // Example of thumb icon (optional)
                thumbContent = {
                    if (simpleSwitchChecked) Icon(Icons.Filled.Check, "On") else Icon(
                        Icons.Filled.Close,
                        "Off"
                    )
                }

                // If you want a thumb icon, you can add it here:
                // thumbContent = { if (switchCheckedState) Icon(...) else Icon(...) }
            )
        }
        Button(
            onClick = { isSwitchEnabled = !isSwitchEnabled },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(if (isSwitchEnabled) "Disable First Switch" else "Enable First Switch")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Example 2: Permanently Disabled Switch
        Text("Disabled Switch Example:", style = MaterialTheme.typography.labelMedium)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                // Not toggleable if it's meant to be permanently disabled for this demo part
                // If it were conditionally disabled but still part of a form, toggleable(enabled=false) is good.
                .padding(vertical = 8.dp, horizontal = 4.dp)
        ) {
            Text(
                text = "Always Disabled Switch",
                modifier = Modifier.padding(end = 16.dp)
            )
            Switch(
                checked = disabledSwitchChecked, // The visual state it's stuck in
                onCheckedChange = { /* This won't be called if enabled is false */ },
                enabled = false // THIS MAKES THE SWITCH DISABLED
            )
        }
    }
}