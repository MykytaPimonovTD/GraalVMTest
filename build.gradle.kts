plugins {
    kotlin("jvm")
    application
    id("org.graalvm.buildtools.native").version("0.10.5")
}

repositories {
    mavenCentral()
    google()
}

kotlin {
    explicitApi()
}

dependencies {
    implementation("com.google.flogger:flogger:0.8")
    implementation("com.google.flogger:flogger-system-backend:0.8")
}

application {
    mainClass = "io.spine.graal.MainKt"
}

graalvmNative {
    toolchainDetection = true

    binaries {
        named("main") {
            imageName = "application"
            mainClass = "io.spine.graal.MainKt"
            fallback = false
        }
    }

    agent {
        enabled= true
        defaultMode = "standard"
    }
}
