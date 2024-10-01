package org.portfolio.project

import kotlinx.browser.window

actual fun getPlatform(): String = "Web"
actual fun getScreenDimensions(): Dimensions {
    return Dimensions(
        width = window.innerWidth,
        height = window.innerHeight
    )
}