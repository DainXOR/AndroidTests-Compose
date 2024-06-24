package net.dain.basicscodelab.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.dain.basicscodelab.MiniApp
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
    private val buttonGradient: Gradient = Gradient(
        start = Color(0, 255, 0, 255),
        end = Color(0, 255, 255, 255),
        forceAlpha = 0f,
        steps = options.size.toUInt()
    ),
    private val textGradient: Gradient = Gradient(
        start = Color.Black,
        end = Color.Black,
        forceAlpha = 0f,
        steps = options.size.toUInt()
    ),
    private val onClick: (MiniApp) -> Unit = {},
    private val blackAndWhiteText: Boolean = true,
    private val adjustTextColor: Boolean = false
) : Collection<SimpleButton>
{
    private var buttons: EnumMap<MiniApp, SimpleButton> = EnumMap(MiniApp::class.java)

    constructor(
        modifier: Modifier = Modifier,
        options: List<MiniApp> = listOf(),
        bgColor: Color = Color(25, 25, 25, 255),
        beginButtonsColor: Color = Color(0, 255, 0, 255),
        endButtonsColor: Color = Color(0, 255, 255, 255),
        beginTextsColor: Color = Color(0, 0, 0, 255),
        endTextsColor: Color = Color(0, 0, 0, 255),
        buttonAlpha: Float = 0f,
        textAlpha: Float = 0f,
        onClick: (MiniApp) -> Unit = {},
        blackAndWhiteText: Boolean = true,
        adjustTextColor: Boolean = false
    ) : this(
        modifier,
        options,
        bgColor,
        Gradient(
            start = beginButtonsColor, end = endButtonsColor,
            forceAlpha = buttonAlpha, steps = options.size.toUInt()
        ),
        Gradient(
            start = beginTextsColor, end = endTextsColor,
            forceAlpha = textAlpha, steps = options.size.toUInt()
        ),
        onClick,
        blackAndWhiteText,
        adjustTextColor
    )

    override fun isEmpty(): Boolean {
        return buttons.isEmpty()
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
    operator fun get(app: MiniApp): SimpleButton {
        return buttons[app]!!
    }

    override val size: Int
        get() = buttons.size

    fun getButtons(): List<SimpleButton> {
        return buttons.values.toList()
    }



    @Composable
    fun Draw() {
        Surface(
            color = bgColor,
            modifier = modifier.fillMaxSize()
        ) {
            val test = options.zip(buttonGradient, textGradient)

            LazyColumn(
                modifier = modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                ) { items(items = options.zip(buttonGradient, textGradient) ) { (option, bColor, tColor) ->

                    val textColor = if (adjustTextColor) {
                        tColor.contrast(
                            bgColor = bColor.solid(),
                            blackOrWhite = blackAndWhiteText
                        )
                    } else {
                        tColor
                    }

                    val newButton = SimpleButton(
                        modifier = Modifier.padding(vertical = 8.dp),
                        onClick = { app: MiniApp -> onClick(app) },
                        app = option,
                        text = option.displayName,
                        bgColor = bColor,
                        textColor = textColor.solid()
                    )

                    newButton.Draw()
                    buttons[option] = newButton
                }
            }
        }
    }
}