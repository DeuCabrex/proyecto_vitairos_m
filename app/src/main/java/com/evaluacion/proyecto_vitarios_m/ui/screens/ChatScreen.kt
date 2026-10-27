package com.evaluacion.proyecto_vitarios_m.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.data.local.entity.ChatMessageEntity
import com.evaluacion.proyecto_vitarios_m.ui.theme.*
import com.evaluacion.proyecto_vitarios_m.ui.viewmodel.ChatViewModel

data class MensajeChat(
    val texto: String,
    val esUsuario: Boolean
)

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    messagesList: List<ChatMessageEntity>,
    onNavigateBack: () -> Unit
) {
    var inputText by remember { mutableStateOf("") }

    val mensajes = if (messagesList.isEmpty()) {
        listOf(
            MensajeChat(
                "Hola, soy VITAIROS-M. Estoy aquí para acompañarte y brindarte un espacio de pausa. ¿De qué te gustaría hablar o qué necesitas en este momento?",
                esUsuario = false
            )
        )
    } else {
        messagesList.map { entity ->
            MensajeChat(
                texto = entity.message,
                esUsuario = entity.sender == "USER"
            )
        }
    }

    val opcionesRapidas = listOf(
        "Tengo ansiedad",
        "Necesito una pausa",
        "Siento frustración"
    )

    fun enviarMensaje(texto: String) {
        if (texto.isBlank()) return

        // Se guarda a traves del ViewModel
        viewModel.sendMessage(texto)
        inputText = ""
    }

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
                text = "Habla con nosotros",
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(mensajes) { msg ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (msg.esUsuario) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Surface(
                        color = if (msg.esUsuario) AzulDigital else AzulSuave,
                        shape = RoundedCornerShape(
                            topStart = 14.dp,
                            topEnd = 14.dp,
                            bottomStart = if (msg.esUsuario) 14.dp else 2.dp,
                            bottomEnd = if (msg.esUsuario) 2.dp else 14.dp
                        ),
                        modifier = Modifier.widthIn(max = 280.dp)
                    ) {
                        Text(
                            text = msg.texto,
                            color = if (msg.esUsuario) FondoPrincipal else NavyPrincipal,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(12.dp),
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            opcionesRapidas.forEach { opcion ->
                SuggestionChip(
                    onClick = { enviarMensaje(opcion) },
                    label = { Text(opcion, fontSize = 11.sp, color = CianTexto) },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = FondoSecundario)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Escribe un mensaje...", color = GrisAzulado, fontSize = 13.sp) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = NavyPrincipal,
                    unfocusedTextColor = NavyPrincipal,
                    focusedBorderColor = AzulDigital,
                    unfocusedBorderColor = GrisAzulado,
                    cursorColor = AzulDigital
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { enviarMensaje(inputText) },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text("Enviar", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = FondoPrincipal)
            }
        }
    }
}