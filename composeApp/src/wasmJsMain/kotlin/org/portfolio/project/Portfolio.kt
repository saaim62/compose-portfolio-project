package org.portfolio.project

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource


val DarkBlueBackground = Color(0xFF0A1F44)
val LightBlueText = Color(0xFFCCD6F6)
val HighlightBlue = Color(0xFF64B5F6)

val heroGradientColors = listOf(
    Color(0xFF0A1F44),
    Color(0xFF1C2E5B),
)

val TitleTypography = TextStyle(
    fontSize = 48.sp,
    fontWeight = FontWeight.Bold,
    color = LightBlueText
)

val SubtitleTypography = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Medium,
    color = LightBlueText
)

val BodyTypography = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    color = LightBlueText
)

@Composable
fun Portfolio() {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 15000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val colors = darkColors(
        primary = LightBlueText,       // Darker blue for main accents
        onPrimary = LightBlueText,          // Light text on blue background
        secondary = HighlightBlue,          // Light blue for highlights
        onSecondary = Color.Black,          // Black text on secondary elements
        surface = DarkBlueBackground,       // Dark blue surface
        onSurface = LightBlueText,          // White text on dark surfaces
        background = DarkBlueBackground,    // Dark blue background
        onBackground = LightBlueText        // White text on the background
    )

    MaterialTheme(
        colors = colors,
        typography = Typography(
            h3 = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = LightBlueText // Light blue for headers
            ),
            h5 = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = LightBlueText // Light blue for subheadings
            ),
            body1 = TextStyle(
                fontSize = 16.sp,
                color = LightBlueText // Light blue text for body content
            ),
            button = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black // Black text on light blue buttons
            )
        )
    ) {
        val scrollState = rememberScrollState()
        val selectedMenuItem = remember { mutableStateOf("About") }

        LaunchedEffect(scrollState) {
            snapshotFlow { scrollState.value }.collectLatest { scrollY ->
                when {
                    scrollY < 550 -> selectedMenuItem.value = "About"
                    scrollY in 550..2124 -> selectedMenuItem.value = "Experience"
                    scrollY >= 2125 -> selectedMenuItem.value = "Projects"
                }
            }
        }

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
                ScrollableScreen(scrollState, selectedMenuItem)
            } else {
                AboutMe(scrollState, selectedMenuItem)
                ScrollableScreen(scrollState, selectedMenuItem)
            }
        }
    }
}

@Composable
fun AboutMe(scrollState: ScrollState, selectedMenuItem: MutableState<String>) {
    val uriHandler: UriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(0.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.2f)
        ) { }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(Res.drawable.myImage),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .padding(top = 24.dp, start = 48.dp)
                    .size(160.dp)
                    .clip(CircleShape)
                    .border(4.dp, HighlightBlue, CircleShape)
                    .shadow(8.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Muhammad Saim", style = TitleTypography)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Senior Software Engineer", style = SubtitleTypography)
            Spacer(modifier = Modifier.height(32.dp))
            MenuSection(scrollState, selectedMenuItem)
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 120.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                SocialIcon(painterResource(Res.drawable.github)) { uriHandler.openUri("https://github.com/saaim62") }
                SocialIcon(painterResource(Res.drawable.linkedin)) { uriHandler.openUri("https://www.linkedin.com/in/muhammad-saim-android-dev/") }
                SocialIcon(painterResource(Res.drawable.twiter)) { uriHandler.openUri("https://github.com/saaim62") }
                SocialIcon(painterResource(Res.drawable.facebook)) { uriHandler.openUri("https://github.com/saaim62") }
                SocialIcon(painterResource(Res.drawable.instagram)) { uriHandler.openUri("https://github.com/saaim62") }
            }
        }
    }
}

@Composable
fun MenuSection(scrollState: ScrollState, selectedMenuItem: MutableState<String>) {
    val coroutineScope = rememberCoroutineScope() // Get the coroutine scope
    val menuItems = listOf("About", "Experience", "Projects")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        menuItems.forEach { item ->
            val isSelected = selectedMenuItem.value == item
            Row(
                modifier = Modifier
                    .clickable {
                        selectedMenuItem.value = item
                        coroutineScope.launch {
                            val scrollPosition = when (item) {
                                "About" -> 0
                                "Experience" -> 550
                                "Projects" -> 2150
                                else -> 0
                            }
                            scrollState.animateScrollTo(scrollPosition)
                        }
                    }
                    .padding(bottom = 8.dp)
            ) {
                val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                val color = if (isSelected) Color.White else LightBlueText
                val textSize = if (isSelected) 18.sp else 16.sp

                Spacer(
                    modifier = Modifier.padding(top = 8.dp).width(if (isSelected) 64.dp else 32.dp)
                        .height(3.dp)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = item,
                    fontWeight = fontWeight,
                    color = color,
                    fontSize = textSize
                )
            }
        }
    }
}

@Composable
fun ScrollableScreen(scrollState: ScrollState, selectedMenuItem: MutableState<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.9f)) {
            if (getScreenDimensions().width < 800) {
                AboutMe(scrollState, selectedMenuItem)
                Experience()
                Projects()
            } else {
                Experience()
                Projects()
            }
        }
        Column(modifier = Modifier.fillMaxWidth(0.1f)) { }
    }
}

@Composable
fun Experience() {
    AboutMyself()
    ExperienceSection()
}

@Composable
fun Projects() {
    ProjectsSection()
    ContactSection()
}
