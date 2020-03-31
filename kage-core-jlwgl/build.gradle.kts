plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    sourceSets {
        jvm()

        val commonMain by getting {
            dependencies {
                api(project(":kage-core"))
                api(kotlin("stdlib-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                val lwjglVersion = "3.2.3"

                api("org.lwjgl:lwjgl:$lwjglVersion")
                api("org.lwjgl:lwjgl:$lwjglVersion:natives-linux")
                api("org.lwjgl:lwjgl-glfw:$lwjglVersion")
                api("org.lwjgl:lwjgl-glfw:$lwjglVersion:natives-linux")
                api("org.lwjgl:lwjgl-opengl:$lwjglVersion")
                api("org.lwjgl:lwjgl-opengl:$lwjglVersion:natives-linux")
            }
        }
    }
}