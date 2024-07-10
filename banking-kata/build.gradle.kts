plugins {
    kotlin("jvm") version "1.8.10"
}

group = "org.katas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("io.kotest:kotest-runner-junit5:5.6.1")
    testImplementation("io.kotest:kotest-assertions-core:5.6.1")
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
}
