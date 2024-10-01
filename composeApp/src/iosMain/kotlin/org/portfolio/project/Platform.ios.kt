package org.portfolio.project
import platform.UIKit.UIScreen

actual fun getPlatform(): String = "iOS"
actual fun getScreenDimensions(): Dimensions {
    val bounds = UIScreen.mainScreen.bounds
    return Dimensions(bounds.size.width.toInt(), bounds.size.height.toInt())
}