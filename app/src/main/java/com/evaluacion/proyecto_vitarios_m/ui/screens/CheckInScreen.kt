package com.evaluacion.proyecto_vitarios_m.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

data class NivelAnimo(val id: Int, val emoji: String, val etiqueta: String)

@Composable
fun CheckInScreen(
    onNavigateBack: () -> Unit,
    onCheckInSaved: () -> Unit
) {
    val context = LocalContext.current

    var selectedMoodId by remember { mutableStateOf<Int?>(null) }
    var selectedCategory by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }

    val nivelesAnimo = listOf(
        NivelAnimo(1, "😫", "Muy mal"),
        NivelAnimo(2, "🙁", "Mal"),
        NivelAnimo(3, "😐", "Regular"),
        NivelAnimo(4, "🙂", "Bien"),
        NivelAnimo(5, "😄", "Excelente")
    )

    val categoriasFila1 = listOf("Estudios", "Trabajo", "Familia")
    val categoriasFila2 = listOf("Salud", "Pareja", "Otro")

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = NavyPrincipal,
        unfocusedTextColor = NavyPrincipal,
        focusedBorderColor = AzulDigital,
        unfocusedBorderColor = GrisAzulado,
        focusedLabelColor = AzulDigital,
        unfocusedLabelColor = GrisAzulado,
        cursorColor = AzulDigital
    )

    fun compartirRegistro() {
        val mood = nivelesAnimo.find { it.id == selectedMoodId }?.etiqueta ?: "No especificado"
        val emoji = nivelesAnimo.find { it.id == selectedMoodId }?.emoji ?: ""
        val textoCompartir = """
            🌱 Mi registro en VITAIROS-M:
            • Estado de ánimo: $emoji $mood
            • Relacionado con: ${if (selectedCategory.isNotEmpty()) selectedCategory else "General"}
            • Nota: ${if (noteText.isNotEmpty()) noteText else "Sin nota"}
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, textoCompartir)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Compartir mi reflexión")
        context.startActivity(shareIntent)
    }

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
                text = "¿Cómo te sientes hoy?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrincipal
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "1. ¿Cómo defines tu ánimo actual?",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = NavyPrincipal
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            nivelesAnimo.forEach { item ->
                val isSelected = selectedMoodId == item.id

                val animatedScale by animateFloatAsState(
                    targetValue = if (isSelected) 1.35f else 1.0f,
                    label = "emojiScale"
                )
                val animatedBorderWidth by animateDpAsState(
                    targetValue = if (isSelected) 2.dp else 0.dp,
                    label = "borderWidth"
                )

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MintSuave else FondoSecundario
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                        .clickable { selectedMoodId = item.id }
                        .border(
                            width = animatedBorderWidth,
                            color = if (isSelected) VerdeContinuidad else FondoSecundario,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.emoji,
                            fontSize = 22.sp,
                            modifier = Modifier.scale(animatedScale)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.etiqueta,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSelected) CianTexto else GrisAzulado,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "2. ¿Con qué se relaciona principalmente?",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = NavyPrincipal
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            categoriasFila1.forEach { cat ->
                val isSelected = selectedCategory == cat
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = if (isSelected) "" else cat },
                    label = { Text(cat, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = AzulSuave,
                        selectedLabelColor = NavyPrincipal,
                        containerColor = FondoSecundario,
                        labelColor = GrisAzulado
                    )
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            categoriasFila2.forEach { cat ->
                val isSelected = selectedCategory == cat
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = if (isSelected) "" else cat },
                    label = { Text(cat, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = AzulSuave,
                        selectedLabelColor = NavyPrincipal,
                        containerColor = FondoSecundario,
                        labelColor = GrisAzulado
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "3. Nota o reflexión rápida (opcional)",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = NavyPrincipal
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = noteText,
            onValueChange = { noteText = it },
            placeholder = { Text("¿Ocurrió algo en particular hoy?", color = GrisAzulado, fontSize = 13.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            textStyle = TextStyle(color = NavyPrincipal, fontSize = 14.sp),
            colors = textFieldColors,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (selectedMoodId == null) {
                    Toast.makeText(context, "Selecciona una carita según tu estado de ánimo", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "¡Registro guardado con éxito!", Toast.LENGTH_SHORT).show()
                    onCheckInSaved()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Guardar mi respuesta",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = FondoPrincipal
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = {
                if (selectedMoodId == null) {
                    Toast.makeText(context, "Selecciona tu ánimo antes de compartir", Toast.LENGTH_SHORT).show()
                } else {
                    compartirRegistro()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "📲 Compartir mi reflexión",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrincipal
            )
        }
    }
}