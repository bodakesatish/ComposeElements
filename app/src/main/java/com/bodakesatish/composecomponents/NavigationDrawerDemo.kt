package com.bodakesatish.composecomponents

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

// Dummy R file for preview if you don't have actual drawables yet.
// In a real app, replace R.drawable.profile_placeholder with your actual drawable resource.
// object R { object drawable { const val profile_placeholder = 0 } }


// Defines a Composable function for this screen.
// This screen demonstrates a ModalNavigationDrawer.
@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar, ModalNavigationDrawer, etc.
@Composable
fun NavigationDrawerExampleScreen(navController: NavController) {
    val context = LocalContext.current

    // `drawerState` controls the open/closed state of the navigation drawer.
    // `rememberDrawerState` ensures the state is preserved across recompositions.
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // `coroutineScope` is used to launch coroutines for animating the drawer (open/close).
    val scope = rememberCoroutineScope()

    // `selectedItemIdentifier` keeps track of which navigation item is currently selected.
    // This is used to highlight the active item in the drawer.
    var selectedItemIdentifier by remember { mutableStateOf(DrawerScreens.Home.identifier) }

    // `currentScreenTitle` state will hold the title of the currently displayed screen.
    var currentScreenTitle by remember { mutableStateOf(DrawerScreens.Home.title) }


    // `ModalNavigationDrawer` is the Composable for the navigation drawer.
    ModalNavigationDrawer(
        drawerState = drawerState, // The state object that controls the drawer.
        // `drawerContent` lambda defines the actual content of the drawer.
        drawerContent = {
            // `ModalDrawerSheet` provides the Material Design styling for the drawer's surface.
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp) // Set a preferred width for the drawer
            ) {
                // Drawer Header: Typically contains branding or user profile info.
                DrawerHeader(
                    modifier = Modifier.padding(16.dp)
                )

                HorizontalDivider() // Visual separator.

                // Navigation items.
                DrawerScreens.values().forEach { screen ->
                    // `NavigationDrawerItem` is a pre-styled component for drawer items.
                    NavigationDrawerItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        // `selected` highlights the item if its identifier matches the current selection.
                        selected = screen.identifier == selectedItemIdentifier,
                        onClick = {
                            // When an item is clicked:
                            selectedItemIdentifier = screen.identifier // Update the selected item.
                            currentScreenTitle = screen.title // Update the screen title.
                            scope.launch { // Launch a coroutine to smoothly close the drawer.
                                drawerState.close()
                            }
                            // In a real app, you would navigate to the corresponding screen here.
                            Toast.makeText(context, "${screen.title} Clicked", Toast.LENGTH_SHORT).show()
                            // Example navigation (replace with your actual navigation logic):
                            // navController.navigate(screen.route) { launchSingleTop = true }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                Spacer(Modifier.weight(1f)) // Pushes logout to the bottom

                HorizontalDivider()
                NavigationDrawerItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, "Logout") },
                    label = { Text("Logout") },
                    selected = false, // Logout is an action, not typically a "selected" screen
                    onClick = {
                        scope.launch { drawerState.close() }
                        Toast.makeText(context, "Logout Clicked", Toast.LENGTH_SHORT).show()
                        // Handle logout logic here
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(modifier = Modifier.height(16.dp)) // Some padding at the bottom
            }
        }
    ) {
        // Main screen content goes here, typically within a Scaffold.
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentScreenTitle) }, // Display the title of the current "screen".
                    navigationIcon = {
                        // The navigation icon (typically a "hamburger" menu icon) opens the drawer.
                        IconButton(onClick = {
                            scope.launch { // Launch a coroutine to smoothly open the drawer.
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Open Navigation Drawer"
                            )
                        }
                    },
                    // Optional: Back button if deeper in navigation stack and not at a root drawer destination
                    actions = {
                        if (navController.previousBackStackEntry != null &&
                            !DrawerScreens.values().any { it.identifier == selectedItemIdentifier }) { // Show back if not a root screen
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            // This is where the content for the selected screen would be displayed.
            // For this example, we just show a text placeholder.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply padding from Scaffold to avoid overlap.
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Content for: $currentScreenTitle",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// Data class representing a screen/destination in the navigation drawer.
enum class DrawerScreens(
    val identifier: String,
    val title: String,
    val icon: ImageVector
    // val route: String // Add this if you integrate with Jetpack Navigation
) {
    Home("home", "Home", Icons.Filled.Home /*, "home_route" */),
    Profile("profile", "Profile", Icons.Filled.Person /*, "profile_route" */),
    Messages("messages", "Messages", Icons.Filled.Email /*, "messages_route" */),
    Settings("settings", "Settings", Icons.Filled.Settings /*, "settings_route" */),
    About("about", "About", Icons.Filled.Info /*, "about_route" */)
}


// Composable function for the Drawer Header content.
@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)) // Light background
            .padding(vertical = 24.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Placeholder for a profile image.
        // Replace R.drawable.profile_placeholder with your actual image resource.
        Image(
//            painter = painterResource(id = android.R.drawable.sym_def_app_icon), // Using a system default icon
            imageVector = Icons.Filled.AccountCircle, // NEW - Use a Material Icon            contentDescription = "Profile Picture",
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape) // Makes the image circular.
                .background(Color.Gray) // Placeholder background if image is transparent
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Your Name", // Replace with actual user name
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = "your.email@example.com", // Replace with actual user email
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
        )
    }
}

// Preview function for Android Studio.
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Drawer Closed")
@Composable
fun PreviewNavigationDrawerExampleScreen() {
    MaterialTheme {
        NavigationDrawerExampleScreen(navController = rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Drawer Open")
@Composable
fun PreviewNavigationDrawerOpen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val scope = rememberCoroutineScope()
    var selectedItemIdentifier by remember { mutableStateOf(DrawerScreens.Home.identifier) }
    var currentScreenTitle by remember { mutableStateOf(DrawerScreens.Home.title) }

    MaterialTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                    DrawerHeader(modifier = Modifier.padding(16.dp))
                    HorizontalDivider()
                    DrawerScreens.values().forEach { screen ->
                        NavigationDrawerItem(
                            icon = { Icon(screen.icon, contentDescription = screen.title) },
                            label = { Text(screen.title) },
                            selected = screen.identifier == selectedItemIdentifier,
                            onClick = {
                                selectedItemIdentifier = screen.identifier
                                currentScreenTitle = screen.title
                                scope.launch { drawerState.close() }
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                    // ... (rest of drawer content for preview)
                }
            },
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(currentScreenTitle) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, "Open Drawer")
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Content for: $currentScreenTitle", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}