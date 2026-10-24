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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

@Composable
fun HomeScreen(
    aliasUsuario: String,
    onNavigateToCheckIn: () -> Unit,
    onNavigateToChat: () -> Unit,
    onNavigateToCrisis: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hola, $aliasUsuario 👋",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )
                Text(
                    text = "¿Cómo va tu día?",
                    fontSize = 14.sp,
                    color = GrisAzulado
                )
            }

            Button(
                onClick = onNavigateToCrisis,
                colors = ButtonDefaults.buttonColors(containerColor = MintSuave),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "¡Ayuda Urgente!",
                    color = CianTexto,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Registro Emocional",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = CianTexto
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "¿Cómo te sientes en este momento?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NavyPrincipal
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Registrar tu estado de ánimo te ayuda a identificar patrones y reflexionar sobre tu día.",
                    fontSize = 13.sp,
                    color = GrisAzulado,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onNavigateToCheckIn,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Hacer Check-in de hoy", fontWeight = FontWeight.Bold)
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = AzulSuave),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Orientación guiada",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = CianTexto
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Hablar con VITAIROS-M",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NavyPrincipal
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Recibe contención, pausas guiadas o sugerencias breves de autocuidado.",
                    fontSize = 13.sp,
                    color = GrisAzulado,
                    lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.height(14.dp))

                OutlinedButton(
                    onClick = onNavigateToChat,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Iniciar conversación", color = NavyPrincipal, fontWeight = FontWeight.Bold)
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "📌 Tu próximo paso",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrincipal
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Realiza una pausa consciente de respiración de 2 minutos antes de continuar tu jornada.",
                    fontSize = 13.sp,
                    color = GrisAzulado,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(
            onClick = onLogout,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cerrar Sesión", color = GrisAzulado, fontSize = 13.sp)
        }
    }
}