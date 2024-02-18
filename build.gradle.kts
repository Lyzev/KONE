import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    `maven-publish`
}

group = project.extra["maven_group"] as String
version = project.extra["maven_version"] as String

repositories {
    mavenCentral()
}

dependencies {
}

kotlin {
    jvmToolchain((project.extra["java_version"] as String).toInt())
}

tasks.getByName<DokkaTask>("dokkaHtml") {
    outputDirectory.set(layout.buildDirectory.get().dir("dokkaHtmlOutput"))
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = project.extra["java_version"] as String
}

tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = project.extra["java_version"] as String
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.extra["maven_group"] as String
            artifactId = project.extra["maven_artifact"] as String
            version = project.extra["maven_version"] as String
            from(components["java"])
        }
    }
}
