package net.dain.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import net.dain.basicscodelab.miniapps.GameActivity
import net.dain.basicscodelab.tests.LayoutsTests
import net.dain.basicscodelab.utils.SimpleMenu
import net.dain.basicscodelab.ui.theme.BasicsCodelabTheme
import net.dain.basicscodelab.utils.Named

enum class MiniApp(override val displayName: String) : Named {
    MAIN_MENU("Main Menu"),
    LAYOUT_TESTING("Layouts Tests"),

    GAME_APP("Game"),
    MUSIC_APP("Music"),
    CHAT_APP("Chat"),
    LOCK_SCREEN("Lock Screen"),

    TEST_1("Test 1"),
    TEST_2("Test 2"),
    TEST_3("Test 3"),
    TEST_4("Test 4"),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BasicsCodelabTheme {
                MainApp()
            }
        }
    }
}


@Composable
fun MainApp(modifier: Modifier = Modifier) {
    var currentWindow by remember { mutableStateOf(MiniApp.MAIN_MENU) }
    val changeWindow: (MiniApp) -> Unit = { menu: MiniApp -> currentWindow = menu }
    val appsCount = MiniApp.entries.lastIndex - 3
    val pages = MiniApp.entries.subList(1, appsCount)

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        when (currentWindow){
            MiniApp.MAIN_MENU -> SimpleMenu(
                modifier = modifier.padding(innerPadding),
                onClick = changeWindow,
                options = pages,
                beginButtonsColor = Color(255, 120, 0),
                endButtonsColor = Color(255, 255, 0)
            ).Draw()

            MiniApp.LAYOUT_TESTING -> LayoutsTests(
                modifier = modifier.padding(innerPadding),
                name = "Dain",
                onClick = changeWindow
            ).Draw()

            MiniApp.GAME_APP -> GameActivity().Draw()
            MiniApp.MUSIC_APP -> TODO()
            MiniApp.CHAT_APP -> TODO()
            MiniApp.LOCK_SCREEN -> TODO()



            MiniApp.TEST_1 -> {}
            MiniApp.TEST_2 -> {}
            MiniApp.TEST_3 -> {}
            MiniApp.TEST_4 -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MainApp()
}