package org.portfolio.project

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import cmp_portfolio_project.composeapp.generated.resources.Res
import cmp_portfolio_project.composeapp.generated.resources.myImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import org.jetbrains.compose.resources.Resource


@Composable
fun App() {
    // Custom MaterialTheme with Professional Colors
    MaterialTheme(
        colors = lightColors(
            primary = Color(0xFF0D47A1),       // Dark Blue
            onPrimary = Color.White,
            secondary = Color(0xFF1976D2),     // Medium Blue
            onSecondary = Color.White,
            surface = Color.White,             // White surface
            onSurface = Color(0xFF212121),     // Dark grey text
            background = Color(0xFFE3F2FD),    // Light Blue background
            onBackground = Color(0xFF212121)   // Dark grey text
        ),
    ) {
        // Background with a gradient of four professional colors
        val gradientColors = listOf(
            Color(0xFF0D47A1), // Dark Blue
            Color(0xFF1565C0), // Medium Dark Blue
            Color(0xFF1976D2), // Medium Blue
            Color(0xFF1E88E5)  // Lighter Blue
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = gradientColors,
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        ) {
            // Make the entire content scrollable
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hero Section with Improvements
                HeroSection()

                Spacer(modifier = Modifier.height(16.dp))

                // Navigation Tabs
                var selectedTab by remember { mutableStateOf(0) }
                val tabTitles = listOf("About", "Experience", "Portfolio", "Skills", "Testimonials", "Contact")

                ScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.primary,
                    edgePadding = 0.dp
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = {
                                Text(
                                    title,
                                    color = if (selectedTab == index) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (selectedTab) {
                    0 -> AboutSection()
                    1 -> ExperienceSection()
                    2 -> PortfolioSection()
                    3 -> SkillsSection()
                    4 -> TestimonialsSection()
                    5 -> ContactSection()
                }
            }
        }
    }
}

@Composable
fun HeroSection() {
    // Define a slightly different gradient for the Hero Section
    val heroGradientColors = listOf(
        Color(0xFF1E88E5), // Blue
        Color(0xFF42A5F5), // Light Blue
        Color(0xFF64B5F6), // Lighter Blue
        Color(0xFF90CAF9)  // Very Light Blue
    )

    // Infinite Transition for Gradient Animation
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        // Background Gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = heroGradientColors,
                        start = Offset(0f, animatedOffset),
                        end = Offset(animatedOffset, 0f)
                    )
                )
        )

        // Overlay for readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        // Foreground Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image with Subtle Glow
            val glowColor = remember { Animatable(Color.Transparent) }
            val targetColor = MaterialTheme.colors.primary.copy(alpha = 0.3f)
            val targetColorState = rememberUpdatedState(targetColor)

            LaunchedEffect(Unit) {
                glowColor.animateTo(
                    targetValue = targetColorState.value,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }

            @OptIn(ExperimentalResourceApi::class)
            Image(
                painter = painterResource(Res.drawable.myImage),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(
                        width = 4.dp,
                        color = glowColor.value,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "M. Saim",
                style = MaterialTheme.typography.h4.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            Text(
                "Android Developer | Kotlin | Java | React Native",
                style = MaterialTheme.typography.subtitle1.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AboutSection() {
    // Fade-in Animation
    val animatedVisibility = remember { MutableTransitionState(false) }
    LaunchedEffect(Unit) {
        animatedVisibility.targetState = true
    }

    AnimatedVisibility(
        visibleState = animatedVisibility,
        enter = fadeIn(animationSpec = tween(1000))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "About Me",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "I am a passionate Android Developer with over 5 years of experience in building high-quality mobile applications. My expertise lies in developing scalable and efficient apps using Kotlin and Java. I enjoy working on challenging projects and staying updated with the latest industry trends.",
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun ExperienceSection() {
    // Slide-in Animation
    val animatedVisibility = remember { MutableTransitionState(false) }
    LaunchedEffect(Unit) {
        animatedVisibility.targetState = true
    }

    AnimatedVisibility(
        visibleState = animatedVisibility,
        enter = slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(1000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Work Experience",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExperienceItem(
                position = "Senior Software Engineer",
                company = "Mavericks United",
                duration = "2020 - Present",
                details = "Developed mobile apps for production and inventory management using Kotlin, MVVM, and more."
            )
            ExperienceItem(
                position = "Senior Software Engineer",
                company = "TheDesignerX",
                duration = "2018 - 2020",
                details = "Developed Android applications to support waste collection, using Kotlin and Android Jetpack."
            )
            ExperienceItem(
                position = "Software Engineer",
                company = "Tech Solutions",
                duration = "2016 - 2018",
                details = "Contributed to various Android projects and collaborated with cross-functional teams."
            )
        }
    }
}

@Composable
fun ExperienceItem(position: String, company: String, duration: String, details: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "$position at $company",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                duration,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                details,
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface)
            )
        }
    }
}

@Composable
fun PortfolioSection() {
    // Zoom-in Animation
    val animatedVisibility = remember { MutableTransitionState(false) }
    LaunchedEffect(Unit) {
        animatedVisibility.targetState = true
    }

    AnimatedVisibility(
        visibleState = animatedVisibility,
        enter = scaleIn(initialScale = 0.8f, animationSpec = tween(1000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Portfolio",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            PortfolioItem(
                projectName = "Inventory Management App",
                description = "A comprehensive app for managing inventory in warehouses.",
                techStack = "Kotlin, MVVM, Retrofit"
            )
            PortfolioItem(
                projectName = "Waste Collection App",
                description = "An Android app supporting waste collection services.",
                techStack = "Kotlin, Android Jetpack, Firebase"
            )
            PortfolioItem(
                projectName = "E-commerce Mobile App",
                description = "Developed a full-featured e-commerce app with payment integration.",
                techStack = "React Native, Redux, Stripe API"
            )
        }
    }
}

@Composable
fun PortfolioItem(projectName: String, description: String, techStack: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                projectName,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                description,
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface)
            )
            Text(
                "Technologies: $techStack",
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            )
        }
    }
}

@Composable
fun SkillsSection() {
    // Fade-in Animation
    val animatedVisibility = remember { MutableTransitionState(false) }
    LaunchedEffect(Unit) {
        animatedVisibility.targetState = true
    }

    AnimatedVisibility(
        visibleState = animatedVisibility,
        enter = fadeIn(animationSpec = tween(1000))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Skills",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                // List of skills
                SkillItem(skill = "Kotlin", proficiency = "Expert")
                SkillItem(skill = "Java", proficiency = "Expert")
                SkillItem(skill = "Android Jetpack", proficiency = "Advanced")
                SkillItem(skill = "React Native", proficiency = "Intermediate")
                SkillItem(skill = "MVVM Architecture", proficiency = "Advanced")
                SkillItem(skill = "RESTful APIs", proficiency = "Advanced")
            }
        }
    }
}

@Composable
fun SkillItem(skill: String, proficiency: String) {
    val proficiencyLevels = mapOf(
        "Beginner" to 0.3f,
        "Intermediate" to 0.6f,
        "Advanced" to 0.8f,
        "Expert" to 1f
    )
    val progress = proficiencyLevels[proficiency] ?: 0f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            skill,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
            modifier = Modifier.width(120.dp)
        )
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        )
    }
}

@Composable
fun TestimonialsSection() {
    // Slide-in Animation
    val animatedVisibility = remember { MutableTransitionState(false) }
    LaunchedEffect(Unit) {
        animatedVisibility.targetState = true
    }

    AnimatedVisibility(
        visibleState = animatedVisibility,
        enter = slideInVertically(initialOffsetY = { 1000 }, animationSpec = tween(1000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Client Testimonials",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TestimonialItem(
                quote = "\"Saim's work is outstanding, and he is great to work with.\"",
                client = "Client A"
            )
            TestimonialItem(
                quote = "\"Excellent mobile solutions, on-time delivery.\"",
                client = "Client B"
            )
            TestimonialItem(
                quote = "\"Highly professional and skilled developer.\"",
                client = "Client C"
            )
        }
    }
}

@Composable
fun TestimonialItem(quote: String, client: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                quote,
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontStyle = FontStyle.Italic
                )
            )
            Text(
                "- $client",
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            )
        }
    }
}

@Composable
fun ContactSection() {
    // Fade-in Animation
    val animatedVisibility = remember { MutableTransitionState(false) }
    LaunchedEffect(Unit) {
        animatedVisibility.targetState = true
    }

    AnimatedVisibility(
        visibleState = animatedVisibility,
        enter = fadeIn(animationSpec = tween(1000))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Contact Me",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                ContactItem(
                    icon = Icons.Default.Email,
                    contactText = "saaim62@gmail.com"
                )
                ContactItem(
                    icon = Icons.Default.Phone,
                    contactText = "+92 320 6090154"
                )
                ContactItem(
                    icon = Icons.Default.Link,
                    contactText = "linkedin.com/in/saim"
                )
                ContactItem(
                    icon = Icons.Default.Web,
                    contactText = "github.com/saim"
                )
            }
        }
    }
}

@Composable
fun ContactItem(icon: ImageVector, contactText: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            contactText,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
        )
    }
}
