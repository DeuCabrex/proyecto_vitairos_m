package com.evaluacion.proyecto_vitarios_m

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.evaluacion.proyecto_vitarios_m.ui.navigation.AppNavigation
import com.evaluacion.proyecto_vitarios_m.ui.theme.FondoPrincipal
import com.evaluacion.proyecto_vitarios_m.ui.theme.Proyecto_Vitarios_MTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto_Vitarios_MTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = FondoPrincipal
                ) {
                    // Cargamos el flujo completo con navegación
                    AppNavigation()
                }
            }
        }
    }
}