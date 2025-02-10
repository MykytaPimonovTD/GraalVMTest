plugins {
    kotlin("jvm")
    id("org.graalvm.buildtools.native").version("0.10.5")
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
    explicitApi()
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
