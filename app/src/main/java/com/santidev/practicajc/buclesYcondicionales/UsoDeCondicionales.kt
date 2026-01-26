package com.santidev.practicasjetpackcompose.buclesYcondicionales

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun UsoDeCondicionalesYBucles() {

  //TODO:Existen 4 tipos de bucles y condicionales...
  //IF
  //WHEN
  //FOR
  //WHILE
  
  //CONDICIONALES SIMPLES =
  //TODO: IF/ ELSE/ ELSE IF
  val edad = 18 //Representa un valor "base"
  if (edad >= 18) { //repsenta la condicion, debe ser mayor O igual a la condicion base
    Text("Podes pasar") // si es mayor O igual a la condicion base
  } else {
    Text("No podes pasar") // si es menor a la condicion base
  }
  
  //TODO:se puede agregar una segunda condicion en caso de que la primera no se cumpla puede cumplirse la segunda.
  //TODO:de forma que puede tener una segunda oportunidad para que funcione
  var nota = 80
  if (nota >= 90) { //condicion principal para que funcione
    Text("Excelente")
  } else if (nota >= 75) { //condicion secundaria y ultima para que funcione
    Text("Muy bien")
  } else {  //condicion negadora , no funciono ninguna de las 2 anteriores
    Text("Debes mejorar")
  }
  
  //---------------------------------------------------------------------------------
  
  //TODO:When de sintaxis basica
  val dia = 5 //<- TODO:Evaluación: when compara el valor de dia (que es 5) con cada caso
  val nombreDia = when (dia) {  //<- TODO:Asignación: El resultado se guarda en nombreDia
    1 -> "Lunes"
    2 -> "Martes"
    3 -> "Miercoles"
    4 -> "Jueves"
    5 -> "Viernes" // <- TODO:Coincidencia: Cuando encuentra el (5, numero necesario) -> ejecuta ese bloque y devuelve "Viernes"
    6 -> "Sabado"
    7 ->  "Domingo"
    else -> "Otro dia"  // <- TODO: else: Es obligatorio cuando when devuelve un valor, maneja casos no previstos
  }
  Text("dia = $nombreDia del: $dia")  //<-TODO: Devuelve el nombre del dia al que corresponde la evaluacion principala (dia: 5 = "Viernes")
  
  //TODO:When de Casos múltiples (días agrupados)
  val numero = 6
  val tipo = when (numero) {
    1, 2, 3, 4, 5 -> "Dia laborable"
    6, 7 -> "Fin de semana"
    else -> "Dia invalido"
  }
  Text("Dia: $tipo del numero de dia: $numero")
  
  //TODO:When de Rangos
  val rango = 8
  val desdeHasta = when (rango) {
    in 1..5 -> "Dia de semana laborables"
    in 6..7 -> "Fin de semana"
    else -> "Dia invalido"
  }
  Text("El rango de $desdeHasta es: $rango")
  
  //TODO:When Sin argumento (como if-else)
  val numeroDelMensaje = 4
  val mensaje = when {
    numeroDelMensaje < 1 -> "Dia muy bajo"
    numeroDelMensaje in 1..5 -> "A trabajar!"
    numeroDelMensaje in 6..7 -> "A descansar!"
    numeroDelMensaje > 7 -> "Dia muy alto"
    else -> "Dia invalido"
  }
  Text("mensaje : $mensaje del numero: $numeroDelMensaje")
  
  //TODO:When Con verificación de tipo
  val entrada: Any = 9
  val resultado = when (entrada) {
    is Int -> when (entrada) {
      in 1..7 -> "Numero de dia valido: $entrada"
      else -> "Numero fuera de rango"
    }
    is String -> "Recibiste texto: $entrada"
    else -> "Tipo no reconocido"
  }
  Text("$resultado")
  
  //TODO:When Como expresión en funciones:
  fun obtenerTipoDia(dia: Int): String = when (dia) {
    in 1..5 -> "Laborable"
    in 6..7 -> "Fin de semana"
    else -> "Invalido"
  }
//  val tipo = obtenerTipoDia(5)  // "Laborable"
  
  //TODO:When Con múltiples instrucciones por caso
  val mensajeDelDia = when (dia) {
    1, 2, 3, 4, 5 -> {
      val tipo = "laborable"
      Text("Es un dia $tipo")
      "Hay que trabajar"  // Valor retornado
    }
    6, 7 -> {
      val tipo = "descanso"
      Text("Es un dia de $tipo")
      "Hay que descansar"  // Valor retornado
    }
    else -> "Dia invalido"
  }
  
  //---------------------------------------------------------------------------------
  
  //LOOPS =
  //TODO: FOR
  
  //TODO: El bucle for en Kotlin itera sobre cualquier cosa que proporcione un iterador (rangos, colecciones, arrays, etc.).
  //TODO: Tiene una Sintaxis básica muy entendible
  
  //for (item in coleccion) { //TODO:itera cada uno de los "item", dentro de "coleccion"
  //  código a ejecutar
  //}
  
  //TODO: 1. Iterando rangos:
  //Rango inclusivo (1 hasta 5)
  for (i in 1..5) {
    // i = "itera"
    // in = "en"
    // 1 .. 5 = "itera los elementos entre 1 y el 5"
    Text("numero del rango = $i")
    //No muestra los elementos 1, 2, 3, 4, 5...
    //muestra cada text con el numero del rango
  }

  // Rango exclusivo (1 hasta 4)
  //TODO: ( .. ) crea rangos inclusivos, ( until ) crea rangos exclusivos
  for (i in 1 until 5) {
    Text("numero del rango, menos el ultimo = $i")  // 1, 2, 3, 4
  }

  // Rango descendente
  for (i in 5 downTo 1) {
    Text("numero del rango al revez = $i")  // 5, 4, 3, 2, 1
  }

  // Con saltos (step)
  //TODO: step define el incremento entre valores, es decir, es la suma de cada iteracion con el step,
  //TODO: 1 + (step 2) = 3... 1 + (step 3) = 4 y asi con cada uno
  for (i in 1..10 step 2) {
    Text("numero de rango mas la suma del 2 $i")  // 1, 3, 5, 7, 9
  }
  
  //TODO: 2. Iterando listas
  
  val dias = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")
  val ids = listOf(2423, 53324, 200012, 93301)

  // Recorrer elementos
  for (dia in dias) { //recorre cada "dia" dentro del listado de "dias"
    Text(dia)
  }

  // Con índice
  //TODO: .indices devuelve el rango de índices válidos
  for (i in dias.indices) {
    Text("Dia $i: ${dias[i]}")
  }

  // Con índice y valor (withIndex)
  for ((indice, dia) in dias.withIndex()) {
    Text("Posicion $indice: $dia")
  }
  
  for (i in ids.indices) {
    Text("cada una de las ids =  :$i ${ids[i]}")
    // $id: hace representacion a la posicion (indice de cada elemento).
    // ${ids} = hace referencia al listado de ids
    // [i] = devuelve cada item dentro del listado
    // $i ${ids[i]} = todo junto seria : muestra la posicion, recorre el listado y de ese listado devuelve cada item
  }
  
  //TODO: 3. Iterando arrays
  
  val numeros = arrayOf(10, 20, 30, 40, 50)
  
  for (numero in numeros) {
    Text("el numero es = $numero")
  }

  // Con índice
  for (i in numeros.indices) {
    //.indices devuelve el rango de índices válidos ejemplo =
    // numero de indice = [1,2,3,4] y numero del listado
    Text("numeros[$i] = ${numeros[i]}")
    //numeros[0] = 10
  }
  
  //TODO: 4. Iterando mapas
  
  val diasSemana = mapOf(
    1 to "Lunes",
    2 to "Martes",
    3 to "Miercoles"
  )
  
  for ((numero, nombre) in diasSemana) {
    Text("el numero del Dia es = $numero: y de nombre = $nombre")
  }
  
  //TODO: 5. Con condiciones (combinando with when/if):
  
  for (i in 1..7) { //Genera el bucle de 1 a 7
    val tipo = when (i) {  // Evalua cada numero del bucle
      in 1..5 -> "Laborable"  // Evalua si esta dentro del rango de 1 a 5 es dia laboral
      else -> "Fin de semana" // si no esta dentro del rango de 1 a 5 es fin de semana
    }
    Text("Dia numero del dia es = $i: y es semana $tipo")
  }
  
  //TODO: 6. Rangos de caracteres:
  
  for (letra in 'a'..'e') {
    Text("$letra")  // a, b, c, d, e
    //se puede jugar con los ejemplos de antes y poder saltear letras, poner un maximo de letras... ETC
  }
  
  //TODO: 7. Break y continue:
  
  // Break (salir del bucle), cuando llega al 6
  for (i in 1..10) {
    if (i == 6) break
    //cuando i es igual a 6, se rompe el bucle y sale del mismo
    Text("llega como maximo al = $i")
  }

  // Continue (saltar iteración)
  for (i in 1..10) {
    if (i == 5) continue
    //cuando i es igual a 5, se salta esa iteracion (ese numero) y continua con el bucle hasta el final del rango
    Text("continua hasta el = $i")
  }
  
  //TODO: WHILE
  //TODO: El bucle while ejecuta un bloque de código repetidamente mientras una condición sea verdadera.
  // Kotlin tiene dos variantes: while y do-while.
  
  //Sintaxis basica
  //while (condicion) {
    // código a ejecutar
  //}
  
  //TODO: 1. While básico
  var diaComun = 1 //dia valor de 1
  while (diaComun <= 5) { // mientras que dia comun sea menor o igual a 5
    Text("Dia $diaComun: Laborable") // Imprime días 1 al 5
    diaComun++ //sumar 1 a diaComun, hasta llegar a la condicion
  }
  
  //TODO: 2. Contador descendente:
  var cuenta = 5 //cuenta tiene un valor de 5
  while (cuenta > 0) {  //mientras que cuenta sea mayor que 0
    Text("Cuenta regresiva: $cuenta")
    cuenta--  //disminuir 1, hasta llegar a la condicion
  }
  Text("¡Despegue!")
  
  //TODO: 3. Búsqueda en lista
  val Semana = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")
  var i = 0  //arracan en la posicion 0 (lunes)
  while (i < Semana.size) {  //mientras que i sea menor al tamaño del listado de Semana
    Text("Dia ${i + 1}: ${Semana[i]}") // imprime el dia y su posicion
    i++  // suma 1 a i, hasta llegar a la condicion
  }
  
  //TODO: 4. Con condición compleja
  var diaNumero = 1 // dias trabajados
  var trabajados = 0  //cantidad de dias trabajados
  while (diaNumero <= 7 && trabajados < 5) {  // mientras que el numero de dias trabajdos sea menor O igual a 7 Y trabajados sea menor a 5
    if (diaNumero <= 5) {  // si los dias trabajados son menor O igual a 5
      Text("Dia $diaNumero: Trabajando")
      trabajados++  // si los dias trabajdos son menor 5 sigue sumando 1 dia
    }
    diaNumero++  // y si el numero de dia es menor de 5, suma 1 al dia
  }
  
  //TODO: 5. do-while (ejecuta al menos una vez)
  // En Kotlin, un do-while siempre ejecuta el bloque al menos una vez, sin importar si la condición es verdadera o falsa.
  var valor = 8
  do {  //El bloque do { } se ejecuta sí o sí, aunque la condición sea falsa.
    Text("Este mensaje aparece aunque dia > 7")  // este mensaje se va a mostrar si o si, no importa lo que pase
    valor++  //valor++  el valor inicial es 8, ahora vale 9
  } while (valor <= 7) //Ahora recién el programa verifica la condición del while:
  //Como la condición es falsa, no repite, y termina el bucle.
  
  //TODO: 6. do-while con validaciónes
  var cada = 0
  do {
    cada = (1..10).random()  // Simula entrada de usuario
    Text("Día ingresado: $cada") //  Muestra la entrada del usuario
    
    when (cada) {  // Evalúa la entrada del usuario
      in 1..5 -> Text("Día laborable válido") // Si está dentro del rango de 1 a 5, muestra un mensaje
      in 6..7 -> Text("Fin de semana válido") // Si está dentro del rango de 6 a 7, muestra un mensaje
      else -> Text("Día inválido, intenta de nuevo") // Si no está dentro del rango, muestra un mensaje
    }
  } while (cada !in 1..7) // Verifica si la entrada del usuario está dentro del rango válido ( 1 a 7 )
  Text("Día válido seleccionado: $cada")
  
  var intentos = 0
  var diaValido = false
  do {
    val dia = (0..8).random()  // Simula input
    intentos++
    
    if (dia in 1..7) {
      Text("Día válido: $dia")
      diaValido = true
    } else {
      Text("Día inválido: $dia, reintentando...")
    }
  } while (!diaValido && intentos < 5)
  
  if (diaValido) {
    Text("Proceso completado")
  } else {
    Text("Máximo de intentos alcanzado")
  }
  
  //TODO:7. Combinando with when:
  var diaOtro = 1
  while (diaOtro <= 7) {
    val tipo = when (diaOtro) {
      in 1..5 -> "Laborable"
      else -> "Fin de semana"
    }
    Text("Dia $diaOtro: $tipo")
    diaOtro++
  }
  
  //TODO: Diferencias clave:
  
  //TODO:While =
  //Verifica condicion primer
  //Puede no ejecutarse nunca
  //Usa cuando no sabes si debe ejecutarse.
  
  
  //TODO:do - while =
  //ejecuta primer y verifica despues
  //se ejecuta al menos una vez
  // usa cuando debe ejecutarse minimo una vez
  
  //----------------------------------------------------------------------------
  
  //TODO:Cuándo usar while vs for??
  //TODO:for: Cuando sabes cuántas iteraciones necesitas (rangos, colecciones)
  //TODO;while: Cuando la condición de parada depende de lógica dinámica
  //TODO:do-while: Cuando necesitas ejecutar al menos una vez (menús, validaciones)
  
}

@Composable
fun MostrarUsoDeCondicionales() {
  UsoDeCondicionalesYBucles()
}