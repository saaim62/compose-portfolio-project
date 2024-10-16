package org.portfolio.project

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AboutMyself() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "About Me",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "With over five years of experience in mobile and web development, I specialize in creating intuitive, robust applications across platforms. My expertise spans native Android development (Java, Kotlin), cross-platform applications using Kotlin Multiplatform (KMP) and Compose Multiplatform (CMP), and full-stack web development with React, Node.js, and Spring Boot. I have delivered successful projects in industries like e-commerce and healthcare, employing CI/CD pipelines, MVVM architecture, and modern testing practices. My collaborative approach and adaptability allow me to thrive in fast-paced, dynamic environments.",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}