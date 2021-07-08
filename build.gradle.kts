val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.20"
}

group = "com.j"
version = "0.0.1"
application {
    mainClass.set("com.j.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:7.0.0")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}