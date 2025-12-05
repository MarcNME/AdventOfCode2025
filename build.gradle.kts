plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.enzmann"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("de.m3y.kformat:kformat:0.14")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
tasks.register<JavaExec>("runMain") {
    group = "application"
    description = "Runs the main function"

    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("MainKt") // or "your.package.MainKt"
}