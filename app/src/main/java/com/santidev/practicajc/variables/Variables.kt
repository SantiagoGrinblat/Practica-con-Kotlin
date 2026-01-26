package com.santidev.practicasjetpackcompose.variables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UsoDeVariables() {

  //PREVENCION DE ERRORES ENTRE VAL Y VAR
  
  val lectura:String = "No puede cambiar su valor, este texto permanecera asi para siempre"
  Text("texto permamente: $lectura", color = Color.Red)
  
  var lecturaYescritura: String = "Puede cambiar, este texto puede cambiar, a numeros, valores Boolean y todo lo que sea necesario"
  Text("Texto original: $lecturaYescritura", color = Color.Yellow)
  
  lecturaYescritura = "Este ya es un texto modificado de la variable VAR"
  Text("Texto modificado: $lecturaYescritura", color = Color.Green)
  //VAR: Si no es modificada debajo de la palabra clave (var), nos pondra un warning.
  //De que como no fue modificada, seria mejor modificar la variable a que no sea de ReEscritura, es decir (val)
  
  //TODO: Como recomendacion de Buenas Practicas, SIEMPRE USAR ( VAL ), como primera opcion
  //TODO: Y solo usar ( VAR ), cuando es una variable que SI O SI debe cambiar, continuamente O que debe cambiar en algun momento
  
}

@Composable
fun MostrarVariables() {
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
//    UsoDeVariables()
  }
}