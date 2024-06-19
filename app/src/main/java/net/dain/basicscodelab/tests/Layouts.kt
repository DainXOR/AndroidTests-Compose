package net.dain.basicscodelab.tests

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Layouts(name: String, modifier: Modifier = Modifier) {
    Surface(
        color = Color(50, 25, 255, 200),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Hmmm...",
            modifier = Modifier.padding(horizontal = 150.dp)
        )
    }
    Surface(
        color = Color(255, 0, 0, 176),
        modifier = modifier.fillMaxHeight()
    ) {
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}