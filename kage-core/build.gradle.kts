plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        jvm()

        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib")
                api("org.jetbrains.kotlin:kotlin-stdlib-common")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
            }
        }
        val jvmMain by getting {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
                api("org.jetbrains.kotlin:kotlin-reflect")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
            }
        }

    }
}