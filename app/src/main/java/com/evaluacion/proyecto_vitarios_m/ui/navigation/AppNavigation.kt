package com.evaluacion.proyecto_vitarios_m.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.evaluacion.proyecto_vitarios_m.ui.screens.ChatScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.CheckInScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.CrisisScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.HomeScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.LoginScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.OnboardingScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.ProfessionalsScreen

enum class Pantalla {
    ONBOARDING,
    LOGIN,
    HOME,
    CHECKIN,
    CHAT,
    CRISIS,
    PROFESIONALES
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
                    pantallaActual = Pantalla.CHECKIN
                },
                onNavigateToChat = {
                    pantallaActual = Pantalla.CHAT
                },
                onNavigateToCrisis = {
                    pantallaActual = Pantalla.CRISIS
                },
                onNavigateToProfessionals = {
                    pantallaActual = Pantalla.PROFESIONALES
                },
                onLogout = {
                    pantallaActual = Pantalla.LOGIN
                }
            )
        }

        Pantalla.CHECKIN -> {
            CheckInScreen(
                onNavigateBack = {
                    pantallaActual = Pantalla.HOME
                },
                onCheckInSaved = {
                    pantallaActual = Pantalla.HOME
                }
            )
        }

        Pantalla.CHAT -> {
            ChatScreen(
                onNavigateBack = {
                    pantallaActual = Pantalla.HOME
                }
            )
        }

        Pantalla.CRISIS -> {
            CrisisScreen(
                onNavigateBack = {
                    pantallaActual = Pantalla.HOME
                }
            )
        }

        Pantalla.PROFESIONALES -> {
            ProfessionalsScreen(
                onNavigateBack = {
                    pantallaActual = Pantalla.HOME
                }
            )
        }
    }
}