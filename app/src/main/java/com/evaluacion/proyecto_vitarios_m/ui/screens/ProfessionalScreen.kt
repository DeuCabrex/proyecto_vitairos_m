package com.evaluacion.proyecto_vitarios_m.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

data class Profesional(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val enfoque: String,
    val disponibilidad: String
)

@Composable
fun ProfessionalsScreen(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    val profesionales = listOf(
        Profesional(1, "Dra. Camila Fuentes", "Psicóloga Clínica", "Terapia Cognitivo-Conductual", "Disponible Hoy"),
        Profesional(2, "Ps. Mateo Silva", "Especialista en Ansiedad", "Mindfulness y Manejo del Estrés", "Mañana a las 15:00"),
        Profesional(3, "Dra. Valentina Ríos", "Terapeuta Adulto Joven", "Psicoterapia Humanista", "Jueves desde las 10:00")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .statusBarsPadding()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            TextButton(onClick = onNavigateBack) {
                Text("← Volver", color = AzulDigital, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Red de Profesionales",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrincipal
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        HorizontalDivider(color = FondoSecundario, thickness = 1.dp)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(profesionales) { prof ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = FondoSecundario),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = prof.nombre,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = NavyPrincipal
                            )
                            Surface(
                                color = MintSuave,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = prof.disponibilidad,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CianTexto,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = prof.especialidad,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AzulDigital
                        )

                        Text(
                            text = "Enfoque: ${prof.enfoque}",
                            fontSize = 12.sp,
                            color = GrisAzulado
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Solicitud enviada para agendar con ${prof.nombre}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(38.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Agendar hora simulada", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = FondoPrincipal)
                        }
                    }
                }
            }
        }
    }
}