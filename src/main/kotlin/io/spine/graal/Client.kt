package io.spine.graal

import com.google.common.flogger.FluentLogger
import io.spine.examples.pingh.client.MentionDetails
import io.spine.examples.pingh.client.PinghApplication
import io.spine.examples.pingh.client.UserAlert

internal fun createApp(): PinghApplication =
    PinghApplication.builder()
        .withAddress("localhost")
        .withPort(50051)
        .with(LoggerAlert())
        .build()

private class LoggerAlert : UserAlert {

    override fun notifyMention(mention: MentionDetails) {
        logger.atInfo()
            .log("Mention: ${mention.toString().replace(System.lineSeparator(), "")}.")
    }

    override fun notifySessionExpired() {
        logger.atInfo().log("Session expired.")
    }

    private companion object {
        private val logger = FluentLogger.forEnclosingClass()
    }
}
