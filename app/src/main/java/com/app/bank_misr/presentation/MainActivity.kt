package com.app.bank_misr.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.app.bank_misr.presentation.destinations.ExceptionScreenDestination
import com.app.bank_misr.presentation.main.CurrencyConverterScreen
import com.app.bank_misr.presentation.theme.AppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val action by lazy { intent.extras?.getString("action") }
  private val message by lazy { intent.extras?.getString("message") }

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    addContent()
  }

  private fun addContent() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContent {
      AppTheme {
        Surface(
          modifier = Modifier.fillMaxSize()
        ) {
          Next()
        }
      }
    }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
      val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
      view.updatePadding(bottom = bottom)
      insets
    }

  }

  @Composable
  private fun Next() {
    DestinationsNavHost(
      navGraph = NavGraphs.root,
      startRoute = when (message) {
        null -> CurrencyConverterScreenDestination
        else -> ExceptionScreenDestination
      }
    ) {
      composable(CurrencyConverterScreenDestination) {
        CurrencyConverterScreen(
        )
      }
    }
  }
}