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
    onNavigateToProfessionals: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hola, $aliasUsuario 👋",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )
                Text(
                    text = "¿Cómo va tu día?",
                    fontSize = 12.sp,
                    color = GrisAzulado
                )
            }

            Button(
                onClick = onNavigateToCrisis,
                colors = ButtonDefaults.buttonColors(containerColor = MintSuave),
                shape = RoundedCornerShape(14.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "🚨 Ayuda Urgente",
                    color = CianTexto,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "REGISTRO EMOCIONAL",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CianTexto,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "¿Cómo te sientes en este momento?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NavyPrincipal
                )
                Text(
                    text = "Identifica patrones y reflexiona sobre tu jornada.",
                    fontSize = 12.sp,
                    color = GrisAzulado
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onNavigateToCheckIn,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Registrar cómo me siento", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = AzulSuave),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "ORIENTACIÓN GUIADA",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CianTexto,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Hablar con VITAIROS-M",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NavyPrincipal
                )
                Text(
                    text = "Recibe contención o sugerencias de autocuidado.",
                    fontSize = 12.sp,
                    color = GrisAzulado
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = onNavigateToChat,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Iniciar conversación", color = NavyPrincipal, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "RED PROFESIONAL",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = CianTexto,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Especialistas y Citas",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NavyPrincipal
                )
                Text(
                    text = "Explora la red de apoyo profesional disponible.",
                    fontSize = 12.sp,
                    color = GrisAzulado
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = onNavigateToProfessionals,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Ver profesionales", color = NavyPrincipal, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onLogout,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cerrar Sesión", color = GrisAzulado, fontSize = 12.sp)
        }
    }
}