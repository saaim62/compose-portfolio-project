package org.portfolio.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Web
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun ContactSection() {
    val uriHandler: UriHandler = LocalUriHandler.current

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
                    onClick = { uriHandler.openUri("mailto:saaim62@gmail.com") }
                )
                ContactItem(
                    icon = Icons.Default.Phone,
                    contactText = "+92 320 6090154",
                    onClick = { /* You could handle phone here if needed */ }
                )
                ContactItem(
                    icon = Icons.Default.Link,
                    contactText = "linkedin.com/in/saim",
                    onClick = { uriHandler.openUri("https://www.linkedin.com/in/muhammad-saim-android-dev") }
                )
                ContactItem(
                    icon = Icons.Default.Web,
                    contactText = "github.com/saaim62",
                    onClick = { uriHandler.openUri("https://github.com/saaim62") }
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
