package org.portfolio.project

data class Dimensions(val width: Int, val height: Int)

expect fun getPlatform(): String
expect fun getScreenDimensions(): Dimensions
//expect fun openUrl(url: String)
