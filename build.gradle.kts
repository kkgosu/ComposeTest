import org.jetbrains.kotlin.konan.properties.Properties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.dipien.releaseshub.gradle.plugin") version "2.0.1"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta04")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
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