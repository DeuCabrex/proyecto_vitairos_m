package com.evaluacion.proyecto_vitarios_m.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

@Composable
fun OnboardingScreen(
    onNavigateToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(
                text = "VITAIROS-M",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrincipal
            )
            Text(
                text = "SALUD MENTAL DIGITAL",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = CianTexto,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "La vida no ocurre solo en la sesión.",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = NavyPrincipal,
                textAlign = TextAlign.Center
            )
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Acompañamiento seguro",
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "VITAIROS-M te ayuda a registrar cómo te sientes, propone actividades de autocuidado y facilita el contacto con profesionales.\n\n" +
                            "⚠️ Importante: Esta aplicación NO realiza diagnósticos, NO prescribe tratamientos ni reemplaza la atención médica o psicológica.",
                    fontSize = 13.sp,
                    color = GrisAzulado,
                    lineHeight = 18.sp
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onNavigateToHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Ingresar como Usuario Demo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = FondoPrincipal
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Tus datos se guardan de forma local y privada en tu dispositivo.",
                fontSize = 11.sp,
                color = GrisAzulado,
                textAlign = TextAlign.Center
            )
        }
    }
}