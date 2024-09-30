package org.portfolio.project

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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
