package net.dain.basicscodelab.tests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.dain.basicscodelab.MiniApp
import net.dain.basicscodelab.utils.SimpleButton

class LayoutsTests(
    var modifier: Modifier = Modifier,
    var onClick: (MiniApp) -> Unit = {},
    var name: String = "World"
) {
    @Composable
    fun Draw(){
        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color(25, 25, 25, 255)
        ) {
            Row {
                Column(modifier = Modifier.fillMaxHeight()) {
                    Surface (modifier = Modifier.fillMaxHeight(), color = Color.Red) {
                        Text(
                            text = "Hello, $name!",
                            color = Color.White,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Surface (
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Blue
                    ) {
                        Text(
                            text = "Hmmm...",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Surface (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SimpleButton(
                            modifier = Modifier.padding(vertical = 8.dp),
                            onClick = { app: MiniApp -> onClick(app) },
                            app = MiniApp.MAIN_MENU,
                            text = "Go back",
                            bgColor = Color(255, 25, 125),
                        ).Draw()
                    }
                }
            }
        }
    }
}