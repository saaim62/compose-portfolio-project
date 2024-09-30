package org.portfolio.project

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import cmp_portfolio_project.composeapp.generated.resources.Res
import cmp_portfolio_project.composeapp.generated.resources.myImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    // Define a matte black and white theme with minimal gold accents
    val colors = darkColors(
        primary = Color(0xFF262626),       // Gold accents (used sparingly)
        onPrimary = Color.White,           // White on gold elements
        secondary = Color(0xFF262626),     // Secondary subtle gold (used for slight highlights)
        onSecondary = Color.Black,         // Black on secondary elements
        surface = Color(0xFF121212),       // Matte black for surfaces
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
                            Color(0xFF121212),    // 80% Matte Black
                            Color(0xFF262626)     // 20% Subtle Gold Gradient
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeroSection()
                Spacer(modifier = Modifier.height(24.dp))
                var selectedTab by remember { mutableStateOf(0) }
                NavigationTabs(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
                Spacer(modifier = Modifier.height(24.dp))
                ContentSection(selectedTab = selectedTab)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

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
            .height(500.dp)
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
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
            Image(
                painter = painterResource(Res.drawable.myImage),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color(0xFFB8860B), CircleShape)  // Gold border around the profile image
                    .shadow(8.dp),
                contentScale = ContentScale.Crop
            )
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


@Composable
fun NavigationTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabTitles = listOf("About", "Experience", "Portfolio", "Skills", "Testimonials", "Contact")

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,  // White text for tabs
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .padding(horizontal = 16.dp),
                color = Color(0xFFB8860B),  // Gold indicator
                height = 3.dp
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
                        color = if (selectedTab == index) Color(0xFFB8860B) else Color.White // Gold for selected, White for others
                    )
                }
            )
        }
    }
}

@Composable
fun ContentSection(selectedTab: Int) {
    val tabContent: List<@Composable () -> Unit> = listOf(
        { AboutSection() },
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
fun AboutSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color(0xFF1C1C1C),  // Dark surface black
        elevation = 6.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("About Me", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "As an experienced Mobile App Developer with expertise in Java, Kotlin, React Native, and a strong foundation in mobile architecture, I am passionate about creating seamless, user-friendly applications across diverse industries. My proficiency in Android SDK, Firebase, RESTful APIs, and performance optimization ensures the development of robust and secure mobile solutions. I thrive in Agile environments, leveraging my knowledge of cross-platform development and modern tools like KMP, Ktor, and CICD pipelines. Dedicated to innovation, I aim to deliver impactful, high-quality applications that meet user needs and business goals.",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify
            )
        }
    }
}

// Additional sections (ExperienceSection, PortfolioSection, etc.) will follow the same style and structure.




@Composable
fun ExperienceSection() {
    // Slide-In from Left Animation
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(1000)),
        exit = slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(1000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                "Work Experience",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.ExtraBold
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
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                "$position at $company",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                duration,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                details,
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface)
            )
        }
    }
}

@Composable
fun PortfolioSection() {
    // Scale-In Animation
    AnimatedVisibility(
        visible = true,
        enter = scaleIn(initialScale = 0.8f, animationSpec = tween(700)),
        exit = scaleOut(targetScale = 0.8f, animationSpec = tween(700))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                "Portfolio",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.ExtraBold
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
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                projectName,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                description,
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSurface)
            )
            Spacer(modifier = Modifier.height(8.dp))
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
    // Fade-In with Slide Animation
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 6.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Skills",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Skill Progress Bars
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
            modifier = Modifier.width(140.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .weight(1f)
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            proficiency,
            style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onSurface)
        )
    }
}

@Composable
fun TestimonialsSection() {
    // Slide-In from Bottom Animation
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(animationSpec = tween(700)),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(animationSpec = tween(700))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                "Client Testimonials",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.ExtraBold
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
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                quote,
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
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
    // Fade-In Animation
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(1000)),
        exit = fadeOut()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 6.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Contact Me",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                ContactItem(
                    icon = Icons.Default.Email,
                    contactText = "saaim62@gmail.com",
                    onClick = { /* TODO: Handle email intent */ }
                )
                ContactItem(
                    icon = Icons.Default.Phone,
                    contactText = "+92 320 6090154",
                    onClick = { /* TODO: Handle phone intent */ }
                )
                ContactItem(
                    icon = Icons.Default.Link,
                    contactText = "linkedin.com/in/saim",
                    onClick = { /* TODO: Handle LinkedIn intent */ }
                )
                ContactItem(
                    icon = Icons.Default.Web,
                    contactText = "github.com/saim",
                    onClick = { /* TODO: Handle GitHub intent */ }
                )
            }
        }
    }
}

@Composable
fun ContactItem(icon: ImageVector, contactText: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() } // Make the row clickable
    ) {
        Icon(icon, contentDescription = contactText, tint = MaterialTheme.colors.primary)
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            contactText,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
        )
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
