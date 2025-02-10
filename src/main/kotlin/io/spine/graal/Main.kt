package io.spine.graal

import com.google.common.flogger.FluentLogger
import io.grpc.ManagedChannelBuilder
import io.spine.client.Client
import io.spine.core.UserId

private val logger = FluentLogger.forEnclosingClass()

public fun main() {
    logger.atInfo()
        .log("Application started!")
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()
    val guestId = UserId.newBuilder()
        .setValue("Guest-User")
        .vBuild()
    val client = Client.usingChannel(channel)
        .withGuestId(guestId)
        .onServerError { message, error ->
            logger.atSevere().log(error.message.toString() + " " + message.toString())
        }
        .build()
    println(client.isOpen)
}
