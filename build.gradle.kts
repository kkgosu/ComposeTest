import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.dipien.releaseshub.gradle.plugin") version "2.0.1"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.Kotlin.gradlePlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

releasesHub {
    dependenciesBasePath = "buildSrc/src/main/java/"
    dependenciesClassNames = listOf("Dependencies.kt")
    excludes = listOf("gradle")

    pullRequestEnabled = true

    gitHubRepositoryOwner = "kkgosu"
    gitHubRepositoryName = "ComposeSberBong"

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())
    val token = properties.getProperty("github_token")

    gitHubWriteToken = token
}