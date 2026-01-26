package com.santidev.practicasjetpackcompose.tiposdedatos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UsoDeTiposDeDatos() {
  
  //TIPOS DE DATOS
  //TODO: COMO BUENA PRACTICA SIEMPRE PONER EL TIPO DE DATO AL QUE HAGA REPRESENTACION
  
  val numericos:String = "Int, Byte, Short, Long, Float, Double"
  val booleanos:String = "Boolean"
  val textos: String = "String, Char"
  
  //TIPO DE DATOS = NUMERICOS
  
  //NUMERO ENTERO
  val age: Int = 23
  Text("age: $age")
  
  //NUMEROS MAYORES
  val cantidadPositiva: Long = 8_000_000_000
  val cantidadNegativa: Long = 8_000_000_000
  Text("cantidadPositiva $cantidadPositiva")
  Text("cantidadNegativa $cantidadNegativa")
  
  //TODO: Tambien se puede definir un Long solamente poniendo la letra (L) detras de un numero
  val numeroLargo = 30L
  Text("numeroLargo $numeroLargo")
  
  //NUMEROS DECIMALES
  //TODO: Por defecto, kotlin entiende que al tener un numero entero seguido de un punto (.) ESO, ES UN NUMERO DECIMAL
  //TODO: por lo tanto tomara el numero como de tipo Double
  val temperatura: Double = 30.1
  val precio: Double = 19.99
  Text("temperatura $temperatura")
  Text("precio $precio")
  
  //TODO: si queremos tener un numero de tipo Float SIEMPRE se tiene que poner la letra (F) al final del numero
  //TODO: puede ser la letra ( F ) en mayuscula O minuscula. pero como buena practica siempre poner el tipo de dato que es
  val pi = 3.1416F
  val otroNumero: Float = 3.1416F
  Text("pi $pi")
  Text("otroNumero $otroNumero")
  
  //---------------------------------------------------------------------------------------------------
  
  //TIPO DE DATO = TEXTOS
  
  //TODO:DIFERENCIAS VISIBLES, DOBLE COMILLAS ( STRING )
  val nombre: String = "Este es un texto de ejemplo"
  Text("nombre $nombre")
  
  //TODO:COMILLAS SIMPLES ( CHARACTER )
  val LetraInicial: Char = 'E'
  Text("LetraInicial $LetraInicial")
  
  //---------------------------------------------------------------------------------------------------
  
  //TIPO DE DATO = BOOLEAN
//  val EstaActivado: Boolean
//  println("$EstaActivado")
  
}

@Composable
fun MostrarTiposDeDatos() {
  Column(modifier = Modifier
    .fillMaxSize()
    .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
//    UsoDeTiposDeDatos()
  }
}