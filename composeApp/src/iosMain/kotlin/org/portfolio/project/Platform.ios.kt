package org.portfolio.project

import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetWidth
import platform.CoreGraphics.CGRectGetHeight
import platform.UIKit.UIScreen

actual fun getPlatform(): String = "iOS"

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenDimensions(): Dimensions {
    val bounds = UIScreen.mainScreen.bounds
    val width = CGRectGetWidth(bounds) // Use CGRectGetWidth to access width
    val height = CGRectGetHeight(bounds) // Use CGRectGetHeight to access height
    return Dimensions(width.toInt(), height.toInt())
}
