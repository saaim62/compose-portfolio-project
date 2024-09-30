package org.portfolio.project

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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
