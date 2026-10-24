package com.evaluacion.proyecto_vitarios_m.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.screens.LoginScreen
import com.evaluacion.proyecto_vitarios_m.ui.screens.OnboardingScreen
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

enum class Pantalla {
    ONBOARDING,
    LOGIN,
    HOME
}

@Composable
fun AppNavigation() {
    // Estado para saber qué pantalla se muestra actualmente
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(FondoPrincipal)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¡Bienvenido/a, $aliasUsuario!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Has ingresado correctamente a VITAIROS-M.",
                    fontSize = 15.sp,
                    color = GrisAzulado,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { pantallaActual = Pantalla.ONBOARDING },
                    colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Cerrar Sesión / Volver al Inicio", color = FondoPrincipal)
                }
            }
        }
    }
}