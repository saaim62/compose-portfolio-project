package org.portfolio.project

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_portfolio_project.composeapp.generated.resources.Res
import cmp_portfolio_project.composeapp.generated.resources.myImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeroSection() {
    val heroGradientColors = listOf(
        Color(0xFF121212),    // Matte Black (80%)
        Color(0xFF3C3C3C)     // Subtle Gold (20%)
    )

    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(500.dp)
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp, topStart = 32.dp, topEnd = 32.dp))
            .background(
                Brush.linearGradient(
                    colors = heroGradientColors,
                    start = Offset(0f, animatedOffset),
                    end = Offset(animatedOffset, 0f)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            @OptIn(ExperimentalResourceApi::class)
            (Image(
                painter = painterResource(Res.drawable.myImage),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color(0xFFB8860B), CircleShape)  // Gold border around the profile image
                    .shadow(8.dp),
                contentScale = ContentScale.Crop
            ))
            Spacer(modifier = Modifier.height(16.dp))

            // Animated Name and Title
            AnimatedText(
                text = "M. Saim", // Name
                style = MaterialTheme.typography.h3.copy(color = Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedText(
                text = "Android Developer | Kotlin | Java | React | React native | KMP | CMP | Ktor | Firebase | Website deployment", // Title or subtitle
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center, color = Color.White)
            )
        }
    }
}
