plugins {
    kotlin("jvm")
    application
    id("org.graalvm.buildtools.native").version("0.10.5")
}

repositories {
    mavenLocal()
    mavenCentral()
    google()
    maven("https://spine.mycloudrepo.io/public/repositories/releases")
}

kotlin {
    explicitApi()
}

dependencies {
    implementation("io.spine.examples.pingh:client:1.0.7")
    implementation("com.google.guava:guava:33.4.0-jre")
    implementation("com.google.flogger:flogger:0.8")
    implementation("com.google.flogger:flogger-system-backend:0.8")
}

val appClassName = "io.spine.graal.MainKt"

application {
    mainClass.set(appClassName)
}

graalvmNative {
    toolchainDetection.set(true)

    binaries {
        named("main") {
            imageName.set("application")
            mainClass.set(appClassName)
            fallback.set(false)
        }
    }

    agent {
        enabled.set(true)
        defaultMode.set("standard")
    }
}
