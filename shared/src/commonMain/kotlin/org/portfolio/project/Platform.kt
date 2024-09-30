package org.portfolio.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform