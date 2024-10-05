package org.portfolio.project

import java.awt.Desktop
import java.awt.Toolkit
import java.net.URI

actual fun getPlatform(): String = "JVM"
actual fun getScreenDimensions(): Dimensions {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    return Dimensions(
        width = screenSize.width,
        height = screenSize.height
    )
}

actual fun openUrl(url: String) {
    Desktop.getDesktop().browse(URI(url))
}