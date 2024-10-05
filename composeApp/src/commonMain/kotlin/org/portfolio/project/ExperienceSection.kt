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
                duration = "May 2023 - Present",
                details = "As a Lead Software Engineer, I led the development and delivery of multiple large-scale applications and projects, steering the team with technical direction, code quality, and project management responsibilities. My key focus areas included:\n" +
                        "• Team Leadership & Technical Mentorship: Managed and mentored a team of developers across various projects, ensuring high-quality software solutions were delivered on time.\n" +
                        "• Architectural Planning & Strategy: Led architectural design and technical strategy, focusing on building scalable and maintainable solutions.\n" +
                        "• CI/CD Pipelines: Set up continuous integration and deployment pipelines, optimizing automated testing and deployments.\n" +
                        "• Mobile & Web Development: Led cross-platform projects, ensuring smooth operations across mobile, desktop, and web environments.\n" +
                        "• Client Communication & Stakeholder Management: Worked closely with clients to understand their requirements and ensure timely project updates."
            )
            ExperienceItem(
                position = "Senior Software Engineer",
                company = "TheDesignerX",
                duration = "Jun 2022 - April 2023",
                details = "Developed Android applications to support waste collection, using Kotlin and Android Jetpack.\n"+"• Worked on full android projects as independent developer\n" +
                        "• Project management of web projects\n" +
                        "• Responsible for creating android applications from scratch\n" +
                        "• Performed project management for estimating and scheduling tasks\n" +
                        "• Designed and coded android applications from specifications, analyses, evaluation and ended at testing and deployment on Google play store\n" +
                        "• Designed effective SEO , SEM , SMM strategies to management and reporting to clients."
            )
            ExperienceItem(
                position = "Software Engineer",
                company = "Connect Bricks",
                duration = "Jan 2022 - May 2022",
                details = "As software Engineer, I Contributed to various Android projects and collaborated with cross-functional teams." +
                        "• Developed Android applications for healthcare providers and patients, focusing on appointment scheduling, remote monitoring, and secure communication\n" +
                        "• Designed and developed applications for online shopping, fashion discovery, and personalized recommendations\n" +
                        "• Continuous Deployment, Unit Testing, and UI Testing to ensure high-quality application development and deployment\n" +
                        "• methodologies, Git, Continuous Integration\n"
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
