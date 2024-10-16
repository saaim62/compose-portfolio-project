package org.portfolio.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        var showLandingPage by remember { mutableStateOf(true) }
        LaunchedEffect(Unit) {
            delay(3000)
            showLandingPage = false
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0A0A0A), // Dark background
                            Color(0xFF1A1A1A)  // Slight gradient to darker tones
                        )
                    )
                )
        ) {
            AnimatedVisibility(
                visible = showLandingPage,
                exit = slideOutVertically(animationSpec = tween(1000)) + fadeOut(animationSpec = tween(1000)),
            ) {
                LandingPage()
            }

            if (!showLandingPage) {
                Portfolio()
            }
        }
    }
}