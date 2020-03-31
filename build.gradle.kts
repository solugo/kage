plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.71" apply false
}

allprojects {
    group = "de.solugo.nexus"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

