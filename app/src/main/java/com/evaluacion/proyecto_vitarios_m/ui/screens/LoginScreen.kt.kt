package com.evaluacion.proyecto_vitarios_m.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evaluacion.proyecto_vitarios_m.ui.theme.*

@Composable
fun LoginScreen(
    onLoginSuccess: (alias: String) -> Unit
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRegisterMode by remember { mutableStateOf(false) }
    var alias by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoPrincipal)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- LOGO Y TÍTULOS ---
        Text(
            text = "VITAIROS-M",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = NavyPrincipal
        )
        Text(
            text = "SALUD MENTAL DIGITAL",
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = CianTexto,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = if (isRegisterMode) "Crear cuenta simulada" else "Iniciar Sesión",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = NavyPrincipal
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (isRegisterMode) {
            OutlinedTextField(
                value = alias,
                onValueChange = { alias = it },
                label = { Text("Nombre o Alias de preferencia") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico simulado") },
            placeholder = { Text("ejemplo@duoc.cl") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val userAlias = if (isRegisterMode && alias.isNotBlank()) alias else email.substringBefore("@")
                    onLoginSuccess(userAlias)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AzulDigital),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if (isRegisterMode) "Registrar e Ingresar" else "Iniciar Sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = FondoPrincipal
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                onLoginSuccess("Usuario Demo")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Ingresar como Usuario Demo",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = NavyPrincipal
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { isRegisterMode = !isRegisterMode }
        ) {
            Text(
                text = if (isRegisterMode) "¿Ya tienes cuenta? Inicia sesión aquí" else "¿No tienes cuenta? Regístrate aquí",
                color = CianTexto,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}