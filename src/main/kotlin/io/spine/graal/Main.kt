package io.spine.graal

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

public fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
}

@Composable
private fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(
            onClick = { text = "Hello, Desktop!" },
            modifier = Modifier.testTag("button")
        ) {
            Text(text)
        }
    }
}