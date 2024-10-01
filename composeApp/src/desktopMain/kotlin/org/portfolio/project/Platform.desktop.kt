package org.portfolio.project

import java.awt.Toolkit

actual fun getPlatform(): String = "JVM"
actual fun getScreenDimensions(): Dimensions {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    return Dimensions(
        width = screenSize.width,
        height = screenSize.height
    )
}