plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ktrofit)
    alias(libs.plugins.serialization)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    //Modules
    implementation(project(":domain"))

    // Unit testing with JUnit
    testImplementation(libs.junit)
    // MockWebServer for mocking HTTP requests
    testImplementation(libs.mockwebserver)

    //KtorFit
    implementation(libs.ktorfit)
    implementation(libs.ktor.converter)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.features)
    implementation(libs.ktor.client)

    //KotlinX Serialization
    implementation(libs.kotlinx.serialization)

    //Koin
    implementation(libs.koin.core)

    //Paging
    implementation(libs.paging)
}
