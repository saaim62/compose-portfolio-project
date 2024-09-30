package org.portfolio.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
