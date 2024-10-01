package org.portfolio.project

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_portfolio_project.composeapp.generated.resources.Res
import cmp_portfolio_project.composeapp.generated.resources.websiteBG
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {

    var showLandingPage by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(3000) // Wait for 3 seconds before switching to the main content
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
            //MainContent()
            Portfolio()
        }
    }
}

@Composable
fun LandingPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        // Background Image Layer
        Image(
            painter = painterResource(Res.drawable.websiteBG),  // Use your background image resource
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),  // Make the image fill the whole screen
            contentScale = ContentScale.Crop    // Crop the image to fit the screen without distortion
        )

        // Text Layer (On top of the image)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Not Your\nAverage\nSoftware\nEngineer",  // Playfair Display font
                style = TextStyle(
                    fontSize = 45.sp,  // Use 45px equivalent in sp
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    fontStyle = FontStyle.Italic,
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Get ready to turn your ideas into reality",  // Montserrat font
                style = TextStyle(
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}

@Composable
fun MainContent() {
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
        // Main Content Container with subtle gold gradient on matte black background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A1A1A), // Lighter Matte Black
                            Color(0xFF262626)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HeroSection()
                Spacer(modifier = Modifier.height(24.dp))
                var selectedTab by remember { mutableStateOf(0) }
                NavigationTabs(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
                Spacer(modifier = Modifier.height(24.dp))
                ContentSection(selectedTab = selectedTab) {
                    selectedTab = 5
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}


@Composable
fun NavigationTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabTitles = listOf("About", "Experience", "Portfolio", "Skills", "Testimonials", "Contact")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        ScrollableTabRow(
            modifier = Modifier.widthIn(max = 600.dp),
            selectedTabIndex = selectedTab,
            backgroundColor = Color.Transparent,
            contentColor = Color.White,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .padding(horizontal = 16.dp),
                    color = Color(0xFFB8860B),
                    height = 3.dp,
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            title,
                            color = if (selectedTab == index) Color(0xFFB8860B) else Color.White
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ContentSection(selectedTab: Int, onContactSelected: () -> Unit) {
    val tabContent: List<@Composable () -> Unit> = listOf(
        { AboutSection(onContactClick = onContactSelected) }, // Pass the callback here
        { ExperienceSection() },
        { PortfolioSection() },
        { SkillsSection() },
        { TestimonialsSection() },
        { ContactSection() }
    )

    AnimatedContent(targetState = selectedTab) {
        tabContent[it]()
    }
}


@Composable
fun AnimatedText(text: String, style: TextStyle) {
    var visible by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut()
    ) {
        Text(
            text = text,
            style = style,
            textAlign = TextAlign.Center
        )
    }
    LaunchedEffect(Unit) {
        visible = true
    }
}

