package org.portfolio.project

import android.content.res.Resources

actual fun getPlatform(): String = "Android"
actual fun getScreenDimensions(): Dimensions {
    val metrics = Resources.getSystem().displayMetrics
    return Dimensions(
        width = metrics.widthPixels,
        height = metrics.heightPixels
    )
}