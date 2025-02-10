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
    implementation("io.spine:spine-client:1.9.0")
    implementation("io.grpc:grpc-netty:1.70.0")

    implementation("com.google.flogger:flogger:0.8")
    implementation("com.google.flogger:flogger-system-backend:0.8")
}

application {
    mainClass.set("io.spine.graal.MainKt")
}

graalvmNative {
    toolchainDetection.set(true)

    binaries {
        named("main") {
            imageName.set("application")
            mainClass.set("io.spine.graal.MainKt")
            fallback.set(false)
        }
    }

    agent {
        enabled.set(true)
        defaultMode.set("standard")
    }
}
