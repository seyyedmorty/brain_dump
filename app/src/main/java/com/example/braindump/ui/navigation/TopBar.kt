package com.example.braindump.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

val titleMap = mapOf(
    Routes.HOME to "داشبورد",
    Routes.DUMP to "سطل زباله",
    Routes.FAVORITE to "نشان شده‌ها",
    Routes.SETTING to "تنظیمات",
    Routes.FEEDBACK to "بازخورد",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    nav: NavController,
    isMenuOpen: Boolean,
    onMenuClick: () -> Unit,
) {
    val navBackStackEntry = nav.currentBackStackEntry
    val currentRoute = navBackStackEntry?.destination?.route

    CenterAlignedTopAppBar(
        title = { Text(
            text = titleMap[currentRoute] ?: "سطل زباله ذهن",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        ) },
        navigationIcon = {
            IconButton(
                onClick = onMenuClick
            ) {
                Icon(
                    imageVector = if (isMenuOpen) Icons.Default.Close else Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        },
        actions = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}