package net.dain.basicscodelab.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

interface Activity {

    @Composable
    fun Draw()

    @Composable
    fun Unimplemented(){
        Surface (
            color = Color(255, 25, 25),
            modifier = Modifier.fillMaxSize()
        ){}
    }
}