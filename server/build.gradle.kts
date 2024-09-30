plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "org.portfolio.project"
version = "1.0.0"
application {
    mainClass.set("org.portfolio.project.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)

    implementation(libs.ktor.server.content.negotiation) // Add Content Negotiation
    implementation(libs.ktor.serialization.jackson)     // Add Jackson Serialization
    implementation(libs.jackson.module.kotlin)          // Add Jackson Kotlin Module
    implementation(libs.swagger.ui)                     // Add Swagger UI
    implementation(libs.swagger.annotations)
}
