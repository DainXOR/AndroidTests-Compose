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
import net.dain.basicscodelab.tests.SimpleMenu
import net.dain.basicscodelab.ui.theme.BasicsCodelabTheme

enum class MiniApp(val displayName: String) {
    MAIN_MENU("Main Menu"),
    LAYOUT_TESTING("Layouts Tests"),

    MUSIC_APP("Music"),
    GAME_APP("Game"),
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
    val appsCount = MiniApp.entries.lastIndex + 1
    val pages = MiniApp.entries.subList(1, appsCount)

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        when (currentWindow){
            MiniApp.MAIN_MENU -> SimpleMenu(
                modifier = modifier.padding(innerPadding),
                onClick = { menu: MiniApp -> currentWindow = menu },
                options = pages,
                beginColor = Color(255, 120, 0),
                endColor = Color(255, 255, 0)
            ).Draw()

            MiniApp.LAYOUT_TESTING -> TODO()
            MiniApp.MUSIC_APP -> TODO()
            MiniApp.GAME_APP -> TODO()
            MiniApp.CHAT_APP -> TODO()
            MiniApp.LOCK_SCREEN -> TODO()



            MiniApp.TEST_1 -> TODO()
            MiniApp.TEST_2 -> TODO()
            MiniApp.TEST_3 -> TODO()
            MiniApp.TEST_4 -> TODO()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MainApp()
}