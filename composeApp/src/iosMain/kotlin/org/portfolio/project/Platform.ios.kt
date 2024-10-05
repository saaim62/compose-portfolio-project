package org.portfolio.project
import platform.UIKit.UIScreen
import platform.Foundation.NSURL

actual fun getPlatform(): String = "iOS"
actual fun getScreenDimensions(): Dimensions {
    val bounds = UIScreen.mainScreen.bounds
    return Dimensions(bounds.size.width.toInt(), bounds.size.height.toInt())
}

actual fun openUrl(url: String) {
    val nsUrl = NSURL.URLWithString(url)!!
    UIApplication.sharedApplication.openURL(nsUrl)
}