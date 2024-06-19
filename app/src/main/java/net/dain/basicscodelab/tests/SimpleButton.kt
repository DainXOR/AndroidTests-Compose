package net.dain.basicscodelab.tests

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.dain.basicscodelab.MiniApp

class SimpleButton(
    private var modifier: Modifier = Modifier,
    private var onClick: (MiniApp) -> Unit = {},
    private var app: MiniApp = MiniApp.MAIN_MENU,
    private var text: String = "Button",
    private var bgColor: Color = Color(255, 0, 255, 255),
    private var textColor: Color = Color(255, 255, 255, 255),
    private var textSize: TextUnit = 16.sp
) {

    fun onClick(newOnClick: (MiniApp) -> Unit): SimpleButton {
        onClick = newOnClick
        return this
    }
    fun text(newText: String): SimpleButton {
        text = newText
        return this
    }
    fun bgColor(newColor: Color): SimpleButton {
        bgColor = newColor
        return this
    }
    fun textColor(newColor: Color): SimpleButton {
        textColor = newColor
        return this
    }
    fun textSize(newSize: TextUnit): SimpleButton {
        textSize = newSize
        return this
    }


    @Composable
    fun Draw() {
        ElevatedButton(
            modifier = modifier.fillMaxWidth(),
            onClick = { onClick(app) },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = bgColor
            )
        ) {
            Text(
                modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                text = text,
                color = textColor,
            )
        }
    }

}