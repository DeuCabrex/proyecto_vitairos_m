package com.evaluacion.proyecto_vitarios_m.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = AzulDigital,
    secondary = CianConexion,
    tertiary = VerdeContinuidad,
    background = NavyPrincipal,
    surface = NavyPrincipal
)

private val LightColorScheme = lightColorScheme(
    primary = AzulDigital,
    secondary = CianConexion,
    tertiary = VerdeContinuidad,
    background = FondoPrincipal,
    surface = FondoSecundario,
    onPrimary = FondoPrincipal,
    onSecondary = NavyPrincipal,
    onTertiary = NavyPrincipal,
    onBackground = NavyPrincipal,
    onSurface = NavyPrincipal
)

@Composable
fun Proyecto_Vitarios_MTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Desactivamos el color dinámico para priorizar siempre los colores de marca de VITAIROS-M
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}