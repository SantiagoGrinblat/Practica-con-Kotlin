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
import com.santidev.practicajc.coroutinesYflows.MostrarUsoDeFlows

@Composable
fun InitialScreen() {
  
  val scroll = rememberScrollState()
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
      .verticalScroll(scroll),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    MostrarUsoDeFlows()
//    MostrarUsoDeCoroutines()
//    MostrarUsoDeLogs()
//    MostrarLambdas()
//    MostrarUsoDeCondicionales()
//    MostrarTiposDeOperadores()
//    MostrarTiposDeDatos()
//    MostrarVariables()
  }
}