package org.portfolio.project

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_portfolio_project.composeapp.generated.resources.Res
import cmp_portfolio_project.composeapp.generated.resources.facebook
import cmp_portfolio_project.composeapp.generated.resources.github
import cmp_portfolio_project.composeapp.generated.resources.instagram
import cmp_portfolio_project.composeapp.generated.resources.linkedin
import cmp_portfolio_project.composeapp.generated.resources.myImage
import cmp_portfolio_project.composeapp.generated.resources.twiter
import org.jetbrains.compose.resources.painterResource

val DarkBackground = Color(0xFF0A192F)
val LightText = Color(0xFFCCD6F6)
val HighlightText = Color(0xFF64FFDA)

val heroGradientColors = listOf(
    Color(0xFF121212),
    Color(0xFF3C3C3C),
)

val TitleTypography = TextStyle(
    fontSize = 36.sp,
    fontWeight = FontWeight.Bold,
    color = Color.White
)

val SubtitleTypography = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Medium,
    color = Color.White
)

val BodyTypography = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    color = Color.White
)

@Composable
fun Portfolio() {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val colors = darkColors(
        primary = Color(0xFF262626),       // Gold accents (used sparingly)
        onPrimary = Color.White,           // White on gold elements
        secondary = Color(0xFF262626),     // Secondary subtle gold (used for slight highlights)
        onSecondary = Color.Black,         // Black on secondary elements
        surface = Color(0xFF121212),
        onSurface = Color.White,           // White text on dark surfaces
        background = Color(0xFF121212),    // Matte black background
        onBackground = Color.White         // White text on matte black background
    )

    MaterialTheme(
        colors = colors,
        typography = Typography(
            h3 = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White // White text for headers
            ),
            h5 = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White // White text for subheadings
            ),
            body1 = TextStyle(
                fontSize = 16.sp,
                color = Color.White // White text for body content
            ),
            button = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black // Black text on buttons with gold background
            )
        )
    ) {
        Row(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = heroGradientColors,
                        start = Offset(0f, animatedOffset),
                        end = Offset(animatedOffset, 0f)
                    )
                )
                .fillMaxSize(),
        ) {
            if (getScreenDimensions().width < 800) {
                ScrollableScreen()
            } else {
                AboutMe()
                ScrollableScreen()
            }
        }
    }
}

@Composable
fun AboutMe() {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.45f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        Text(text = "Muhammad Saim", style = TitleTypography)
        Text(text = "Senior Software Engineer", style = SubtitleTypography)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Android Developer | Kotlin | Java | React | React native | KMP | CMP | Ktor | Firebase | Web deployment.",
            style = BodyTypography
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            SocialIcon(painterResource(Res.drawable.github), {})
            SocialIcon(painterResource(Res.drawable.linkedin), {})
            SocialIcon(painterResource(Res.drawable.twiter), {})
            SocialIcon(painterResource(Res.drawable.facebook), {})
            SocialIcon(painterResource(Res.drawable.instagram), {})
        }
    }
}

@Composable
fun ScrollableScreen() {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Column {
            if (getScreenDimensions().width < 800) {
                AboutMe()
                Experience()
                Projects()
            } else {
                Experience()
                Projects()
            }
        }
    }
}

@Composable
fun Experience() {
    ExperienceSection()
    SkillsSection()
}

@Composable
fun Projects() {
    TestimonialsSection()
    ContactSection()
}