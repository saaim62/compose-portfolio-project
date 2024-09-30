package org.portfolio.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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
