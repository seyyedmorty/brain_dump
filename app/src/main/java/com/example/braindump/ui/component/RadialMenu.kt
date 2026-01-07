package com.example.braindump.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

val menuItems = listOf(
    Icons.Default.Favorite,
    Icons.Default.Settings,
    Icons.Default.Star,
    Icons.Filled.Delete,
    Icons.Default.Home,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RadialMenu(
    isMenuOpen: Boolean,
    onToggle: () -> Unit,
    onItemClick: (Int) -> Unit,
) {


    val radius = 90.dp
    val density = LocalDensity.current
    val radiusPx = with(density) { radius.toPx() }
    val animatedProgress = remember { Animatable(0f) }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (isMenuOpen || animatedProgress.value > 0f) {

        LaunchedEffect(isMenuOpen) {

            keyboardController?.hide()

            animatedProgress.animateTo(
                targetValue = if (isMenuOpen) 1f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
                .clickable { onToggle() }
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        onToggle()
                    })
                },
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(
                onClick = onToggle,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                elevation = FloatingActionButtonDefaults.elevation(12.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Menu"
                )
            }


            menuItems.forEachIndexed { index, icon ->
                val angle = (2 * Math.PI / menuItems.size) * index - 16 * (Math.PI / 180)
                val x = radiusPx * cos(angle) * animatedProgress.value
                val y = radiusPx * sin(angle) * animatedProgress.value

                Surface(
                    modifier = Modifier
                        .offset { IntOffset(x.roundToInt(), y.roundToInt()) }
                        .size(65.dp)
                        .shadow(elevation = 8.dp, shape = CircleShape),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    onClick = {
                        onToggle()
                        onItemClick(index)
                    }
                ) {
                    // Box برای مرکز چین کردن آیکون
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Menu Item $index",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }


            }
        }
    }
}