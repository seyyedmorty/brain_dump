package com.example.braindump.ui.component

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlin.time.Duration.Companion.seconds

@Composable
fun DoubleBack(onExit: () -> Unit) {
    val ctx = LocalContext.current
    var lastBackPressedTime by remember { mutableStateOf(0L) }
    val exitInterval = 2.seconds.inWholeMilliseconds

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPressedTime < exitInterval) {
            onExit()
        } else {
            lastBackPressedTime = currentTime
            Toast.makeText(ctx, "برای خروج دوباره کلیک کن", Toast.LENGTH_SHORT).show()
        }
    }
}