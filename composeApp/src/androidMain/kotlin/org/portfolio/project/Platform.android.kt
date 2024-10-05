package org.portfolio.project

import android.content.Intent
import android.content.res.Resources
import android.net.Uri

actual fun getPlatform(): String = "Android"
actual fun getScreenDimensions(): Dimensions {
    val metrics = Resources.getSystem().displayMetrics
    return Dimensions(
        width = metrics.widthPixels,
        height = metrics.heightPixels
    )
}

//actual fun openUrl(url: String) {
//    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//    ContextHolder.context.startActivity(intent)
//}
