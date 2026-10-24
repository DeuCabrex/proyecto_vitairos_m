package com.evaluacion.proyecto_vitarios_m.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.evaluacion.proyecto_vitarios_m.ui.screens.HomeScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.LoginScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.OnboardingScreen

enum class Pantalla {
    ONBOARDING,
    LOGIN,
    HOME
}

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    var pantallaActual by remember { mutableStateOf(Pantalla.ONBOARDING) }
    var aliasUsuario by remember { mutableStateOf("Usuario Demo") }

    when (pantallaActual) {
        Pantalla.ONBOARDING -> {
            OnboardingScreen(
                onNavigateToHome = {
                    pantallaActual = Pantalla.LOGIN
                }
            )
        }

        Pantalla.LOGIN -> {
            LoginScreen(
                onLoginSuccess = { alias ->
                    aliasUsuario = alias
                    pantallaActual = Pantalla.HOME
                }
            )
        }

        Pantalla.HOME -> {
            HomeScreen(
                aliasUsuario = aliasUsuario,
                onNavigateToCheckIn = {
                    Toast.makeText(context, "Próximamente: Check-in Emocional", Toast.LENGTH_SHORT).show()
                },
                onNavigateToChat = {
                    Toast.makeText(context, "Próximamente: Chat de Orientación", Toast.LENGTH_SHORT).show()
                },
                onNavigateToCrisis = {
                    Toast.makeText(context, "Próximamente: Canales de Emergencia (*4141)", Toast.LENGTH_LONG).show()
                },
                onLogout = {
                    pantallaActual = Pantalla.LOGIN
                }
            )
        }
    }
}