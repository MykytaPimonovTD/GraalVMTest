package io.spine.graal

import com.google.common.flogger.FluentLogger

private val logger = FluentLogger.forEnclosingClass()

public fun main() {
    logger.atInfo()
        .log("Application started!")
}
