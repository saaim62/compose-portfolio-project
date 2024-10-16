package org.portfolio.project

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import cmp_portfolio_project.composeapp.generated.resources.Res
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cmp_portfolio_project.composeapp.generated.resources.Connectavo
import cmp_portfolio_project.composeapp.generated.resources.FaithConnect
import cmp_portfolio_project.composeapp.generated.resources.HarvestYards
import cmp_portfolio_project.composeapp.generated.resources.SAllGood
import cmp_portfolio_project.composeapp.generated.resources.Seated
import cmp_portfolio_project.composeapp.generated.resources.SteezVibe
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.skia.Drawable

@Composable
fun ProjectsSection() {
    // State to track the currently selected project
    var selectedProject by remember { mutableStateOf<Project?>(null) }

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
                "Projects",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // List of Projects
            ProjectItem(
                name = "Seated",
                description = "A restaurant reservation app to get cash back up to 30%.",
                imageResId = Res.drawable.Seated,
                onClick = { selectedProject = Project("Seated", "Seated is a restaurant reservation app designed to provide users with a unique dining experience by offering cashback rewards of up to 30% on their dining reservations. This app focuses on enhancing user engagement by incentivizing bookings with significant savings, creating a win-win situation for both restaurants and customers.\n\nIt is Native Android app developed using MVVM structure and Koin as Dependency Injection", Res.drawable.Seated) }
            )
            ProjectItem(
                name = "Connectavo",
                description = "A mobile app designed for maintenance and management of company machines, spare parts, and operations, streamlining maintenance workflows and improving overall efficiency.",
                imageResId = Res.drawable.Connectavo,
                onClick = {
                    selectedProject = Project(
                        "Connectavo",
                        "Connectavo is a comprehensive mobile solution that helps companies manage their machine maintenance and inventory tracking. It allows for real-time monitoring of machinery, spare parts management, and efficient scheduling of maintenance activities. The app improves operational efficiency by automating routine processes and offering insights through data analysis.\n\nThis project was developed using Android Native, following the MVVM pattern, with Koin for Dependency Injection and Retrofit for network requests.",
                        Res.drawable.Connectavo
                    )
                }
            )
            ProjectItem(
                name = "Steez Vibe",
                description = "An e-commerce platform featuring roles for Cloth Stylist, User, and an E-commerce Store, providing personalized styling and seamless shopping experiences.",
                imageResId = Res.drawable.SteezVibe,
                onClick = {
                    selectedProject = Project(
                        "Steez Vibe",
                        "Steez Vibe is a React Native e-commerce application that connects users with professional cloth stylists and offers a wide range of apparel from various brands. The app features role-based functionality, allowing users to shop or get personalized recommendations from stylists. It provides a smooth navigation experience between different sections, such as the store, stylist consultations, and user wardrobe.\n\nThe app's design incorporates modern UI elements with dynamic spacing and image rendering for product displays. Built using React Native, with Node js for backend services such as authentication and data management.",
                        Res.drawable.SteezVibe
                    )
                }
            )

            ProjectItem(
                name = "Harvest Yards",
                description = "A food donation app that connects donors and receivers to minimize food waste and support communities.",
                imageResId = Res.drawable.HarvestYards,
                onClick = {
                    selectedProject = Project(
                        "Harvest Yards",
                        "Harvest Yards is a React Native application designed to facilitate food donations by connecting donors with receivers. The app allows users to donate surplus food, while receivers can request or claim donations in their area. With role-based functionality, the app ensures seamless interaction between donors and receivers, promoting community support and reducing food waste.\n\nBuilt using React Native for cross-platform mobile development, with Firebase handling the backend services, including authentication, real-time data synchronization, and notifications.",
                        Res.drawable.HarvestYards
                    )
                }
            )

            ProjectItem(
                name = "S'All Good",
                description = "A versatile check-in mobile application offering location updates and communication features to keep users connected and informed.",
                imageResId = Res.drawable.SAllGood,
                onClick = {
                    selectedProject = Project(
                        "S'All Good",
                        "S'All Good is a React Native application designed to provide users with convenient check-in functionalities at various locations. It allows users to notify selected contacts about their location with ease. In addition to location updates, the app includes calling features to ensure seamless communication when necessary. With a focus on user-centric design and practical utilities, S'All Good enhances both convenience and safety, keeping users connected in various situations.\n\nThe app is built using React Native for cross-platform development and utilizes Firebase for backend services such as authentication, real-time location tracking, and notifications.",
                        Res.drawable.SAllGood
                    )
                }
            )

            ProjectItem(
                name = "Faith Connect",
                description = "A mobile application designed to foster spiritual connection and community engagement through announcements, event management, and interactive forums.",
                imageResId = Res.drawable.FaithConnect,
                onClick = {
                    selectedProject = Project(
                        "Faith Connect",
                        "Faith Connect is a robust React Native mobile application built to strengthen spiritual connections and enhance community engagement. It offers a range of features for religious organizations to stay connected with their members through announcements, event scheduling, and interactive discussion forums. The app empowers users to participate in meaningful conversations, stay informed about religious gatherings, and build a sense of community.\n\nWith its user-friendly interface and scalable architecture, Faith Connect ensures that users can easily navigate between various features while staying connected to their faith and community activities.",
                        Res.drawable.FaithConnect
                    )
                }
            )

        }
    }

    if (selectedProject != null) {
        ProjectDetailModal(
            project = selectedProject!!,
            onDismiss = { selectedProject = null }
        )
    }
}

@Composable
fun ProjectItem(name: String, description: String, imageResId: DrawableResource, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Project Image
            Image(
                painter = painterResource(imageResId),
                contentDescription = "$name Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                // Project Name
                Text(
                    name,
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    description,
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    ),
                    maxLines = 5
                )
            }
        }
    }
}

@Composable
fun ProjectDetailModal(project: Project, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colors.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Make the modal content scrollable using a Column and verticalScroll
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()) // Enable scrolling
            ) {
                // Close button
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                // Project Image (with a minimum size of 300dp)
                Image(
                    painter = painterResource(project.imageResId),
                    contentDescription = "${project.name} Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Project Title
                Text(
                    project.name,
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Project Full Description
                Text(
                    project.description,
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
            }
        }
    }
}



// Data class for Project
data class Project(val name: String, val description: String, val imageResId: DrawableResource)
