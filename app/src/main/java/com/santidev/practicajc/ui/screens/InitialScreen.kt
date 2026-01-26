package com.santidev.practicajc.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.santidev.practicajc.debugging.MostrarUsoDeLogs
import com.santidev.practicajc.lambdas.MostrarLambdas
import com.santidev.practicasjetpackcompose.buclesYcondicionales.MostrarUsoDeCondicionales
import com.santidev.practicasjetpackcompose.operadores.MostrarTiposDeOperadores
import com.santidev.practicasjetpackcompose.tiposdedatos.MostrarTiposDeDatos
import com.santidev.practicasjetpackcompose.variables.MostrarVariables

@Composable
fun InitialScreen() {
  
  val scroll = rememberScrollState()
  
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(16.dp)
    .verticalScroll(scroll),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
  
//    MostrarUsoDeLogs()
//    MostrarLambdas()
//    MostrarUsoDeCondicionales()
//    MostrarTiposDeOperadores()
//    MostrarTiposDeDatos()
//    MostrarVariables()
  }
}