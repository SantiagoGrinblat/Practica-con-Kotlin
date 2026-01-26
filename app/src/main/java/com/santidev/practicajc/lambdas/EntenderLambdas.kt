package com.santidev.practicajc.lambdas

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
fun EntenderLambdas() {
  
  //TODO: LAMBDA =
  //Con lambdas podemos evitar clases anonimas, simplificar el codigo y trabajar con funciones de orden superior.
  
  //TODO:¿Qué son las funciones de orden superior?
  //En Kotlin (y en muchos lenguajes), una función de orden superior es:
  //Una función que puede recibir otra función como parámetro, o devolver una función como resultado.
  //Es decir, las funciones pueden trabajar con otras funciones como si fueran datos normales.
  
  //TODO:¿Por qué existen?
  //Porque permiten escribir código:
  //más reutilizable , más expresivo ,más flexible , con menos repetición
  //Son la base de cosas como map, filter, forEach, onClick en Compose, etc.
  
  // Forma general de una lambda:
  // val nombreLambda: (Parametros) -> TipoRetorno = { parametros ->
  //     // código
  //     valorRetorno    // la última expresión es lo que devuelve
  // }
  
  // Ejemplo básico:
  val suma: (Int) -> Int = { x ->
    // x es el parámetro que recibe la lambda
    x + x    // esta es la expresión que se devuelve
  }
  
  // Al llamar a la lambda (suma), pasamos el valor de x (4)
  val sumaResult = suma(4)   // x = 4
  Text("mostramos resultado: $sumaResult")  //y obtenemos el resultado de la suma (8)
  
  // TODO: 2. Usando 'it' (parámetro implícito)
  // Cuando una lambda tiene un solo parámetro, Kotlin permite usar 'it' sin necesidad de nombrarlo explícitamente.
  // TODO:IMPORTANTE:
  // TODO:En este caso, el único parámetro NO es toda la lista.
  // TODO:El parámetro es CADA ELEMENTO dentro de la lista.
  // Kotlin llama a la lambda una vez por cada elemento: "Lunes", "Martes", "Miércoles", etc.
  
  val dias = listOf("Lunes", "Martes", "Miercoles", "jueves", "viernes")
  
  // Ejemplo con forEach: recorre cada elemento de la lista
  dias.forEach { Text(it) }
  // agarramos la variable dias, la recorreremos y mostramos el texto de cada parametro (los dias)
  // Equivalente a escribir:
  // dias.forEach { dia -> Text(dia) }
  
  // Ejemplo filtrando días que empiezan con 'M'
  val diasConM = dias.filter { it.startsWith("M") }
  // una vez que recorremos todos los parametros los filtramos.
  // y seleccionamos cada parametro (it). y le decimos que solo lea, los parametros que comiencen (startsWith) con la letra M
  
  val diasSin = dias.filter { it.endsWith("s") }
  //hacemos lo mismo pero le decimos que filtre por la ultima letra (s)
  
  Text("Los días son: $diasConM")
  Text("Los días son: $diasSin")
  
  //TODO: 3. Lambdas con colecciones (map, filter, forEach)
  // vamos a usar un ejemplo real del dia a dia para entender a profundidad del ejemplo.
  
  data class Producto(
    val nombre: String,
    val precio: Double,
    val enStock: Boolean
  )  // tenemos una data class de producto de tienda REAL
  
  val productos = listOf(
    Producto("Auriculares", 15000.0, true),
    Producto("Mouse", 8500.0, false),
    Producto("Teclado", 22000.0, true),
    Producto("Monitor", 140000.0, true),
    Producto("Parlantes", 30000.0, false)
  ) //Informacion que debemos mostrar al usuario y que se filtre segun la necesidad
  
  //map → transformar productos en texto
  val nombres =
    productos.map { producto -> // mapeamos el nombre de cada producto y devolvemos el producto
      Text("${producto.nombre} - $${producto.precio}")  // mostramos el nombre del prodcuto y su precio
    }
  
  // filter → obtener solo los productos en stock
  val disponibles =
    productos.joinToString(", ") { it.enStock.toString() } // mapeamos por el producto que esta en stock O cual NO
  Text("Disponibles: $disponibles")  // mostramos si esta disponible O no
  
  // forEach → mostrar todos en Composable
  productos.forEach { producto ->  // recorremos todos los productos y mostramos su nombre y precio
    Text("Producto: ${producto.nombre} - Precio: $${producto.precio}")
  }
  
  //TODO: 5. Lambdas como parámetros de función
  //Uso Común	Descripción	Ejemplo Real
  //filter =	Seleccionar elementos que cumplen una condición. //Filtrar usuarios activos de una lista.
  //map =	Transformar cada elemento de una lista a un nuevo tipo.	Convertir una lista de objetos de base de datos (UserEntity) a objetos de dominio (User).
  //forEach =	Ejecutar una acción simple por cada elemento.	Imprimir cada nombre.
  //find =	Encontrar el primer elemento que cumple una condición.	Buscar el primer producto que tiene el ID específico.
  //groupBy =	Agrupar elementos en un mapa según una propiedad.	Agrupar una lista de facturas por el nombre del cliente.
  
  data class Usuario(
    val id: Int,
    val activo: Boolean,
    val rol: String
  )
  
  val usuarios = listOf(
    Usuario(1, true, "Admin"),
    Usuario(2, false, "User")
  )
  
  val nombresUsuariosActivos = usuarios
    .filter { it.activo }.joinToString { it.rol }
  Text("Usuarios activos: $nombresUsuariosActivos")
  // Resultado: "Admin"
  
  //TODO:¿Cuándo SÍ es Necesario Usar .map?
  // el .map sigue siendo esencial en un escenario: donde necesitas hacer algo más con los datos transformados antes de unirlos o terminar el flujo.
  // Escenario : necesitar un (sort) ejemplo =
  // .filter { ... }.map { it.rol }.sorted().joinToString()...Debes mapear a it.rol primero para poder ordenar la lista de String antes de unirla.
  // Necesitas un (distinct) ejemplo =
  // .filter { ... }.map { it.rol }.distinct().joinToString()...Si quieres eliminar roles duplicados, debes mapear a it.rol primero para que distinct opere sobre los roles, no sobre los objetos Usuario completos.
  // El siguiente paso NO es joinToString.filter { ... }.map { it.rol }.find { it == "Admin" ....Si quieres encontrar un rol, primero debes mapear la lista de objetos a una lista de roles (Strings).
  // si el último paso es únicamente generar una cadena de texto, la forma de ( .filter { ... }.joinToString { it.rol } ) es la mejor práctica en Kotlin.
  
  //TODO: 6. Lambdas en llamadas a APIs
  //  Con Coroutines
  
//  interface ApiService {
//    @GET("usuarios/{id}")
//    suspend fun obtenerUsuario(@Path("id") id: Int): Usuario
//
//    @GET("usuarios")
//    suspend fun listarUsuarios(): List<Usuario>
//  }
  
  //TODO:Función con lambda de resultado... forma tradicial
  // El Ejemplo con (Callback)
  // Reintroduce el patron callback
  // La ejecucion salta a la lambda
  //suspend fun obtenerUsuarioSafe(
  //  id: Int,
  //  onResult: (Result<Usuario>) -> Unit
  //) {
  //  try {
  //    val usuario = apiService.obtenerUsuario(id)
  //    onResult(Result.success(usuario))
  //  } catch (e: Exception) {
  //    onResult(Result.failure(e))
  //  }
  //}
  
  //TODO:Ya no necesita el parámetro 'onResult'... forma mejorada
  // version Idiomatica (devuelve el valor)
  // permite que el codigo se lea lineal
  // el resultado se asigna a una variable
  //suspend fun obtenerUsuarioSafe(id: Int): Result<Usuario> {
  //  return try {
  //    // Llama a la función suspendida del API
  //    val usuario = apiService.obtenerUsuario(id)
  //    Result.success(usuario)
  //  } catch (e: Exception) {
  //    Result.failure(e)
  //  }
  //}

  // Uso en ViewModel o Activity... forma tradicional
  //  viewModelScope.launch {
  //    obtenerUsuarioSafe(1) { result ->
  //      result.onSuccess { usuario ->
  //        println("Usuario: ${usuario.rol}")
  //      }.onFailure { error ->
  //        println("Error: ${error.message}")
  //      }
  //    }
  //  }

  //forma mas moderna
  //  viewModelScope.launch {
  //    // El código se ve lineal y síncrono.
  //    val result = obtenerUsuarioSafe(1)
  //
  //    result.onSuccess { usuario ->
  //      // Si fue exitoso
  //      println("Usuario: ${usuario.rol}")
  //    }.onFailure { error ->
  //      // Si hubo un error
  //      println("Error: ${error.message}")
  //    }
  //  }
  
}

@Composable
fun MostrarLambdas() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EntenderLambdas()
  }
}