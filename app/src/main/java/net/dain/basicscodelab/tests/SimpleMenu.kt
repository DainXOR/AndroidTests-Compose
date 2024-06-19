package net.dain.basicscodelab.tests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.dain.basicscodelab.MiniApp
import net.dain.basicscodelab.utils.Gradient
import net.dain.basicscodelab.utils.contrast
import java.util.EnumMap

@Preview(showBackground = true)
@Composable
fun LayoutsPreview() {
    SimpleMenu(options = listOf(
        MiniApp.MAIN_MENU,
        MiniApp.TEST_1,
        MiniApp.TEST_2,
        MiniApp.TEST_3,
        MiniApp.TEST_4
    )).Draw()
}

class SimpleMenu(
    private val modifier: Modifier = Modifier,
    private val options: List<MiniApp> = listOf(),
    private val bgColor: Color = Color(25, 25, 25, 255),
    private val gradient: Gradient = Gradient(
        start = Color(0, 255, 0),
        end = Color(0, 255, 255),
        forceAlpha = 0f,
        steps = options.size.toUInt()
    ),
    private val onClick: (MiniApp) -> Unit = {},
    private val blackAndWhiteText: Boolean = true
) : Collection<SimpleButton>
{
    private var buttons: EnumMap<MiniApp, SimpleButton> = EnumMap(MiniApp::class.java)

    constructor(
        modifier: Modifier = Modifier,
        options: List<MiniApp> = listOf(),
        bgColor: Color = Color(25, 25, 25, 255),
        beginColor: Color = Color(0, 255, 0),
        endColor: Color = Color(0, 255, 255),
        alphaValue: Float = 0f,
        onClick: (MiniApp) -> Unit = {},
        blackAndWhiteText: Boolean = true
    ) : this(
        modifier,
        options,
        bgColor,
        Gradient(
            start = beginColor, end = endColor,
            forceAlpha = alphaValue, steps = options.size.toUInt()
        ),
        onClick,
        blackAndWhiteText
    )

    override fun isEmpty(): Boolean {
        return options.isEmpty()
    }
    override fun iterator(): Iterator<SimpleButton> {
        return buttons.values.iterator()
    }
    override fun containsAll(elements: Collection<SimpleButton>): Boolean {
        return buttons.values.toList().containsAll(elements)
    }
    override fun contains(element: SimpleButton): Boolean {
        return buttons.values.contains(element)
    }

    private operator fun get(index: Int): SimpleButton {
        return buttons.values.elementAt(index)
    }

    override val size: Int
        get() = buttons.size


    @Composable
    fun Draw() {
        Surface(
            color = bgColor,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                ) {
                val gradientText = gradient.copy()

                for (i in options.indices) {
                    val newButton = SimpleButton(
                        modifier = Modifier.padding(vertical = 8.dp),
                        onClick = { app: MiniApp -> onClick(app) },
                        app = options[i],
                        text = options[i].displayName,
                        bgColor = gradient[i],
                        textColor = gradientText[i].contrast(
                            bgColor = gradient[i],
                            minContrast = 0.8f,
                            blackOrWhite = blackAndWhiteText
                        )
                    )

                    newButton.Draw()
                    buttons[options[i]] = newButton
                }


            }
        }
    }
}