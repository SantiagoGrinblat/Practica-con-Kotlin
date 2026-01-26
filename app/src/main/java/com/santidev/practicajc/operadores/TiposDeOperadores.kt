package com.santidev.practicasjetpackcompose.operadores

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
fun TiposDeOperadores() {
  
  //TIPOS DE OPERADORES
  
  //TODO: ARITMETICOS = para operaciones matematicas
  //TODO: ASIGNACION = para actualizar valores
  //TODO: COMPARACION = para verificar igualdad O desigualdad
  //TODO: LOGICOS = para combinar condiciones
  
  //--------------------------------------------------------------------------
  
  //OPERADORES ARITMETICOS
  val OperadorSumar = 5 + 3
  //println(OperadorSuma) <- resultado: 8
  Text(text = "$OperadorSumar")
  
  val OperadorRestar = 10 - 2
  //println(OperadorRestar) <- resultado: 8
  Text(text = "$OperadorRestar")
  
  val OperadorMultiplicar = 4 * 2
  //println(OperadorMultiplicar) <- resultado: 8
  Text(text = "$OperadorMultiplicar")
  
  val OperadorDividisor = 16 / 2
  //println(OperadorDividisor) <- resultado: 8
  Text(text = "$OperadorDividisor")
  
  val OperadorDeResto = 17 % 3
  //println(OperadorDeResto) <- resultado : 2
  Text(text = "$OperadorDeResto")
  
  //--------------------------------------------------------------------------
  
  //OPERADORES DE ASIGNACION
  var numero = 10
  numero = numero + 1
  numero += 1 //ESTA SERIA UNA FORMA DIFERENTE DE HACER LO MISMO
  //println(numero) <- resultado: 11
  Text(text = "$numero")
  
  //TODO: Se puede hacer con todos los operadores de la misma manera.
  
  var otroNumero = 10
  otroNumero = otroNumero * 2
  otroNumero *= 2 //ESTA SERIA UNA FORMA DIFERENTE DE HACER LO MISMO
  //println(otroNumero) <- resultado: 20
  Text(text = "$otroNumero")
  
  //--------------------------------------------------------------------------
  
  //OPERADORES DE COMPARACION
  //TODO: los operadores de comparacion devuelven TRUE o FALSE
  
  //TODO: OPERADOR DE COMPARACION
  // a < b : A es MENOR que B
  // a > b : A es MAYOR que B
  // a >= b : A es MAYOR O IGUAL que B
  // a <= b : A es MENOR O IGUAL que B
  
  //TODO: OPERADOR DE IGUALDAD O DESIGUALDAD
  // a == b : A es IGUAL que B
  // a != b : A es DIFERENTE que B
  // a === b : A es EL MISMO OBJETO que B
  // a !== b : A NO es EL MISMO OBJETO que B
  
  //TODO: OPERADOR DE INCREMENTO O DECREMENTO
  // a ++ : A se incrementa en 1
  // a -- : A se decrementa en 1
  
  //TODO: OPERADOR DE NEGACION O CONVERSION O/A BOOLEAN
  // +a : A se convierte en positivo
  // -a : A se convierte en negativo
  // !a : A se convierte en booleano contrario, es decir, si era TRUE se convierte en FALSE y viceversa.
  //la logica es ( !a ) , no es true, por lo tanto al negar el valor, se convierte en false
  
  //TODO: OPERADOR DE RANGO
  // a 1..10 : A es un rango de 1 a 10
  // 1 !a : 1 no esta en el rango de A
  // a !in b : A no esta en el rango de B
  // a in b : A esta en el rango de B
  
  //TODO: OPERADOR DE ACCESO INDEXADO (ARREGLOS)
  // a[i]
  // a[i,j]
  // a[i_1,..,i_n]
  // a[i] = b
  // a[i,j] = b
  // a[i_1,..,i_n] = b
  //HAGAMOS UN EJEMPLO PARA ENTEDER ESTA LOGICA MEJOR =
  
  //TODO: EJMPLOS DE ACCESO INDEXADO (ARREGLOS)
  //  1. a[i] : “A es el elemento en la posición i de la lista A”
  //  fun main() {
  //    val a = listOf(10, 20, 30, 40)
  //    recordar que las posiciones comienzan en 0 y no en 1
  //    en este caso seria ( 0 = 10, 1 = 20, 2 = 30, 3 = 40 )
  //    val elemento = a[2]   // posición 2 → valor 30
  //    println(elemento) // 30
  //  }
  
  //  2. a[i, j] : “A es el elemento en la posición i, j de la matriz A”
  //  En Kotlin no existe el acceso a[i, j] para matrices, pero el libro habla a nivel teórico.
  //  En Kotlin se hace así:
  //  fun main() {
  //    val matriz = arrayOf(
  //      intArrayOf(1, 2, 3), <- fila 0 (horizontal), columna 0 (vertical)
  //      intArrayOf(4, 5, 6), <- fila 1 (horizontal), columna 1 (vertical)
  //      intArrayOf(7, 8, 9), <- fila 2 (horizontal), columna 2 (vertical)
  //    )
  //    val valor = matriz[1][2]
  //    // fila 1, columna 2 → 6
  //    println(valor) // 6
  //  }
  //  📌 En teoría, a[i,j] = "elemento en la fila i y columna j".
  //  📌 En Kotlin se escribe: a[i][j]
  
  //  3. a[i₁, …, iₙ] : posición en matrices multidimensionales
  //  Ejemplo de matriz 3D (cubo de datos):
  //  fun main() {
  //    val cubo = Array(2) { Array(2) { IntArray(2) } }
  //    cubo[1][0][1] = 99
  //    println(cubo[1][0][1]) // 99
  //  }
  //  📌 Esto es la versión Kotlin de a[i₁, i₂, i₃].
  
  
  //TODO:ESTE ES SUPER IMPORTANTE
  //  4. a[i] = b : reemplazar el elemento en posición i por b
  //  fun main() {
  //    val a = mutableListOf(10, 20, 30)
  //    a[1] = 999  // reemplaza el valor en la posición 1
  //    println(a) // [10, 999, 30]
  //  }
  //  📌 Aquí a[1] = 999 es equivalente a a.set(1, 999).
  
  //  5. a[i, j] = b : reemplazar elemento en matriz
  //  fun main() {
  //    val matriz = arrayOf(
  //      intArrayOf(1, 2),
  //      intArrayOf(3, 4)
  //    )
  //    matriz[0][1] = 100
  //    println(matriz[0][1]) // 100
  //  }
  
  //--------------------------------------------------------------------------
  
  //OPERADORES LOGICOS
  
  
}

@Composable
fun MostrarTiposDeOperadores() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
//    TiposDeOperadores()
  }
}