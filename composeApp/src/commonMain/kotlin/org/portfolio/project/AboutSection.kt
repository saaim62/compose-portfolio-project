package org.portfolio.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_portfolio_project.composeapp.generated.resources.*
import cmp_portfolio_project.composeapp.generated.resources.Res
import cmp_portfolio_project.composeapp.generated.resources.github
import cmp_portfolio_project.composeapp.generated.resources.linkedin
import cmp_portfolio_project.composeapp.generated.resources.twiter
import org.jetbrains.compose.resources.painterResource

@Composable
fun AboutSection(onContactClick: () -> Unit) {
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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onContactClick,
                modifier = Modifier.padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text("Contact me", color = Color.White)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialIcon(painterResource(Res.drawable.github),{})
                SocialIcon(painterResource(Res.drawable.linkedin),{})
                SocialIcon(painterResource(Res.drawable.twiter),{})
                SocialIcon(painterResource(Res.drawable.facebook),{})
                SocialIcon(painterResource(Res.drawable.instagram),{})
            }
        }
    }
}

@Composable
fun SocialIcon(iconResId: Painter, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp) // Adjust size as needed
            .background(Color.Gray, shape = RoundedCornerShape(8.dp)) // Add background and corner shape
            .padding(4.dp) // Optional padding
    ) {
        IconButton(onClick = onClick, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = iconResId,
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}



