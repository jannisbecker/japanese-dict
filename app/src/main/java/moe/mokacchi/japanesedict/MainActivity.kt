package moe.mokacchi.japanesedict

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import moe.mokacchi.japanesedict.ui.Navigation
import moe.mokacchi.japanesedict.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Navigation()
            }
        }
    }
}