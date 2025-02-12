package io.spine.graal

import io.spine.examples.pingh.client.EnterUsername
import io.spine.examples.pingh.client.LoginFailed
import io.spine.examples.pingh.client.PinghApplication
import io.spine.examples.pingh.client.VerifyLogin
import io.spine.examples.pingh.github.Username
import io.spine.examples.pingh.github.of
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

internal class Login(private val app: PinghApplication) {

    internal fun start() {
        val flow = app.startLoginFlow()
        while (!flow.isCompleted()) {
            val stage = flow.currentStage()
            when (val screenStage = stage.value) {
                is EnterUsername -> screenStage.start()
                is VerifyLogin -> screenStage.start()
                is LoginFailed -> screenStage.start()
            }
        }
        println("Login completed.")
    }

    private fun EnterUsername.start() {
        println("Enter username:")
        val usernameValue = "MykytaPimonovTD"
        val username = Username::class.of(usernameValue)
        val future = CompletableFuture<Unit>()
        requestUserCode(username) { future.complete(Unit) }
        future.get(5, TimeUnit.SECONDS)
    }

    private fun VerifyLogin.start() {
        val future = CompletableFuture<Unit>()
        waitForAuthCompletion { future.complete(Unit) }
        println(
            "Your verification code is \"${userCode.value.value}\". " +
                    "Enter this code at: ${verificationUrl.value.spec}."
        )
        future.get(expiresIn.value.seconds, TimeUnit.SECONDS)
    }

    private fun LoginFailed.start() {
        println("Login failed. Let's try again!")
    }
}
