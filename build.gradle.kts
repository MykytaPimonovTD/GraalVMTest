import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
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

dependencies {
    implementation(compose.desktop.currentOs)
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
        enabled = true
        defaultMode = "standard"
    }
}

compose.desktop {
    application {
        mainClass = "io.spine.graal.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg)
            packageName = "GraalApplication"
            packageVersion = "1.0.0"
        }
    }
}
