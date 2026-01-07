package com.example.braindump

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.braindump.ui.component.RadialMenu
import com.example.braindump.ui.navigation.AppNavGraph
import com.example.braindump.ui.navigation.Routes
import com.example.braindump.ui.navigation.TopBar
import com.example.braindump.ui.theme.BrainDumpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            var isMenuOpen by remember { mutableStateOf(false) }

            BrainDumpTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(
                            navController,
                            isMenuOpen = isMenuOpen,
                            onMenuClick = { isMenuOpen = !isMenuOpen }
                        )
                    },
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    ) {
                        AppNavGraph(navController, Modifier.fillMaxSize())

                        RadialMenu(
                            isMenuOpen = isMenuOpen,
                            onToggle = { isMenuOpen = !isMenuOpen },
                            onItemClick = { index ->
                                isMenuOpen = false
                                when (index) {
                                    0 -> navController.navigate(Routes.FAVORITE)
                                    1 -> navController.navigate(Routes.SETTING)
                                    2 -> navController.navigate(Routes.FEEDBACK)
                                    3 -> navController.navigate(Routes.DUMP)
                                    4 -> navController.navigate(Routes.HOME)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

