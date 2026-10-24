package com.evaluacion.proyecto_vitarios_m.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

@Composable
fun CrisisScreen(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = onNavigateBack) {
                Text("← Volver", color = AzulDigital, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Ayuda Urgente",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrincipal
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = MintSuave),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "🚨 ¿Estás atravesando una crisis?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Si sientes que no puedes mantenerte a salvo o necesitas hablar con alguien de inmediato, utiliza los siguientes recursos gratuitos y confidenciales.",
                    fontSize = 12.sp,
                    color = GrisAzulado,
                    lineHeight = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Línea de Prevención del Suicidio",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )
                Text(
                    text = "Fono *4141 - Minsal (Gratuito, 24/7)",
                    fontSize = 12.sp,
                    color = GrisAzulado
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:*4141"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Llamar al *4141", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = FondoPrincipal)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "Salud Responde Chile",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )
                Text(
                    text = "Fono 600 360 7777 (Orientación sanitaria)",
                    fontSize = 12.sp,
                    color = GrisAzulado
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:6003607777"))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Llamar al 600 360 7777", color = NavyPrincipal, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = AzulSuave),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "🧘 Ejercicio rápido de conexión a tierra",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "• Nombra 5 cosas que veas a tu alrededor.\n• 4 cosas que puedas tocar.\n• 3 sonidos que escuches.\n• 2 olores que percibas.\n• 1 respiración profunda.",
                    fontSize = 12.sp,
                    color = NavyPrincipal,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "VITAIROS-M es una herramienta de acompañamiento y no reemplaza la atención médica, psicológica o psiquiátrica de urgencia.",
            fontSize = 11.sp,
            color = GrisAzulado,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            lineHeight = 15.sp
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}