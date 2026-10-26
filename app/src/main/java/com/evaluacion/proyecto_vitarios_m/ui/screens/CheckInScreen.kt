package com.evaluacion.proyecto_vitarios_m.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*
import com.evaluacion.proyecto_vitarios_m.ui.viewmodel.CheckInViewModel

data class MoodOption(
    val id: Int,
    val emoji: String,
    val label: String,
    val color: Color
)

data class CategoryOption(
    val id: Int,
    val label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckInScreen(
    viewModel: CheckInViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val moods = listOf(
        MoodOption(1, "😢", "Muy mal", Color(0xFFEF5350)),
        MoodOption(2, "🙁", "Mal", Color(0xFFFF9800)),
        MoodOption(3, "😐", "Regular", Color(0xFFFFCA28)),
        MoodOption(4, "🙂", "Bien", Color(0xFF66BB6A)),
        MoodOption(5, "😄", "Excelente", Color(0xFF26A69A))
    )

    val categories = listOf(
        CategoryOption(1, "Trabajo"),
        CategoryOption(2, "Estudios"),
        CategoryOption(3, "Familia"),
        CategoryOption(4, "Salud"),
        CategoryOption(5, "Pareja"),
        CategoryOption(6, "Personal")
    )

    var selectedMood by remember { mutableStateOf<MoodOption?>(null) }
    var selectedCategory by remember { mutableStateOf<CategoryOption?>(null) }
    var noteText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .statusBarsPadding()
            .padding(bottom = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            TextButton(onClick = onNavigateBack) {
                Text(" Volver", color = AzulDigital, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Check-in Emocional",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = NavyPrincipal
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onNavigateToHistory) {
                Text("Historial", color = AzulDigital, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }

        HorizontalDivider(color = FondoSecundario, thickness = 1.dp)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "¿Cómo te sientes en este momento?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    moods.forEach { mood ->
                        val isSelected = selectedMood?.id == mood.id
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { selectedMood = mood }
                                .padding(4.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) mood.color.copy(alpha = 0.2f)
                                        else FondoSecundario
                                    )
                                    .border(
                                        width = if (isSelected) 2.dp else 0.dp,
                                        color = if (isSelected) mood.color else Color.Transparent,
                                        shape = CircleShape
                                    )
                            ) {
                                Text(text = mood.emoji, fontSize = 26.sp)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = mood.label,
                                fontSize = 10.sp,
                                color = if (isSelected) mood.color else GrisAzulado,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = selectedMood != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "¿Con qué se relaciona?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = NavyPrincipal
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.height(100.dp)
                        ) {
                            items(categories) { cat ->
                                val isCatSelected = selectedCategory?.id == cat.id
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isCatSelected) AzulDigital else FondoSecundario)
                                        .clickable { selectedCategory = cat }
                                        .padding(vertical = 10.dp)
                                ) {
                                    Text(
                                        text = cat.label,
                                        fontSize = 12.sp,
                                        color = if (isCatSelected) Color.White else NavyPrincipal,
                                        fontWeight = if (isCatSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Nota o comentario (opcional)",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = NavyPrincipal
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = noteText,
                            onValueChange = { noteText = it },
                            placeholder = { Text("Escribe algo que quieras recordar...", fontSize = 12.sp, color = GrisAzulado) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = FondoSecundario,
                                unfocusedContainerColor = FondoSecundario,
                                focusedBorderColor = AzulDigital,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                    }
                }
            }

            Button(
                onClick = {
                    selectedMood?.let { mood ->
                        viewModel.guardarCheckIn(
                            moodId = mood.id,
                            category = selectedCategory?.label ?: "",
                            note = noteText,
                            onFinished = {
                                onNavigateToHistory()
                            }
                        )
                    }
                },
                enabled = selectedMood != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulDigital,
                    disabledContainerColor = GrisAzulado.copy(alpha = 0.3f)
                )
            ) {
                Text(
                    text = "Guardar Registro",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}