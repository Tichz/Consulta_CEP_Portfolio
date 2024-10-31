package com.binteckh.consultacep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.binteckh.consultacep.navigation.NavGraph
import com.binteckh.consultacep.navigation.Routes
import com.binteckh.consultacep.ui.components.common.BottomNavigationBar
import com.binteckh.consultacep.ui.theme.ConsultaCEPTheme
import com.binteckh.consultacep.ui.theme.systemBarsColor
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConsultaCEPTheme(dynamicColor = false) {
                val navController = rememberNavController()

                // Cria um objeto TextSelectionColors para personalizar a cor do handler
                val customTextSelectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.tertiary,
                    backgroundColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.35f)
                )

                CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController = navController) }
                    ) { innerPadding ->
                        NavGraph(
                            navController = navController,
                            startDestination = Routes.Search.route,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
                setSystemBarsColor(systemBarsColor = systemBarsColor)
            }
        }
    }

    private fun setSystemBarsColor(systemBarsColor: Color) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)

        // Define a cor da barra de navegação e da barra de status
        window.navigationBarColor = systemBarsColor.toArgb()
        window.statusBarColor = systemBarsColor.toArgb()

        // Define a aparência dos ícones com base no tema
        controller.isAppearanceLightNavigationBars = true
        controller.isAppearanceLightStatusBars = true
    }
}