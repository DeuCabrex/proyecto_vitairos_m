package com.evaluacion.proyecto_vitarios_m.ui.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit
) {
    var aliasInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }

    var isAliasError by remember { mutableStateOf(false) }
    var aliasErrorMessage by remember { mutableStateOf("") }

    var isEmailError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }

    fun validarAlias(valor: String): Boolean {
        val textoLimpio = valor.trim()
        return when {
            textoLimpio.isEmpty() -> {
                isAliasError = true
                aliasErrorMessage = "El alias o nombre no puede estar vacío"
                false
            }
            textoLimpio.length < 3 -> {
                isAliasError = true
                aliasErrorMessage = "El alias debe tener al menos 3 caracteres"
                false
            }
            else -> {
                isAliasError = false
                aliasErrorMessage = ""
                true
            }
        }
    }

    fun validarEmail(valor: String): Boolean {
        val textoLimpio = valor.trim()
        return if (textoLimpio.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(textoLimpio).matches()) {
            isEmailError = true
            emailErrorMessage = "Ingresa un formato de correo válido (ej: usuario@correo.com)"
            false
        } else {
            isEmailError = false
            emailErrorMessage = ""
            true
        }
    }

    fun validarFormulario(): Boolean {
        val aliasValido = validarAlias(aliasInput)
        val emailValido = validarEmail(emailInput)
        return aliasValido && emailValido
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "VITAIROS-M",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = NavyPrincipal
        )

        Text(
            text = "Acompañamiento & Bienestar Emocional",
            fontSize = 13.sp,
            color = GrisAzulado
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = FondoSecundario),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Iniciar Sesión",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyPrincipal
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = aliasInput,
                    onValueChange = {
                        aliasInput = it
                        if (isAliasError) validarAlias(it)
                    },
                    label = { Text("Tu Nombre o Alias *", fontSize = 13.sp) },
                    isError = isAliasError,
                    supportingText = {
                        if (isAliasError) {
                            Text(text = aliasErrorMessage, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
                        }
                    },
                    trailingIcon = {
                        if (isAliasError) {
                            Icon(Icons.Filled.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = NavyPrincipal,
                        unfocusedTextColor = NavyPrincipal,
                        focusedBorderColor = AzulDigital,
                        unfocusedBorderColor = GrisAzulado,
                        cursorColor = AzulDigital
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = emailInput,
                    onValueChange = {
                        emailInput = it
                        if (isEmailError) validarEmail(it)
                    },
                    label = { Text("Correo electrónico (Opcional)", fontSize = 13.sp) },
                    isError = isEmailError,
                    supportingText = {
                        if (isEmailError) {
                            Text(text = emailErrorMessage, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
                        }
                    },
                    trailingIcon = {
                        if (isEmailError) {
                            Icon(Icons.Filled.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = NavyPrincipal,
                        unfocusedTextColor = NavyPrincipal,
                        focusedBorderColor = AzulDigital,
                        unfocusedBorderColor = GrisAzulado,
                        cursorColor = AzulDigital
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        if (validarFormulario()) {
                            onLoginSuccess(aliasInput.trim())
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Ingresar", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = FondoPrincipal)
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = {
                        onLoginSuccess("Usuario Demo")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Ingresar como Usuario Demo", fontSize = 13.sp, color = NavyPrincipal, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}