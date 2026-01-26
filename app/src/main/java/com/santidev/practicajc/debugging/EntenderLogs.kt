package com.santidev.practicajc.debugging

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import java.io.IOException

@Composable
fun EntenderLogs() {
  
  //TODO: ENTENDER QUE ES UN LOG CUANDO USARLO Y SU FORMA DE USARLO
  //TODO:¿Qué son los Logs?
  //Los logs son mensajes que tu app envía a la consola (Logcat) mientras se ejecuta.
  // Son como dejar "migas de pan" en el código para saber qué está pasando, encontrar errores, y entender el flujo de ejecución.
  
  //TODO:¿Para qué sirven?
  //Debugging: Ver qué valores tienen tus variables en tiempo de ejecución
  //Rastrear el flujo: Saber qué funciones se ejecutan y en qué orden
  //Encontrar errores: Ver exactamente dónde y por qué falla la app
  //Monitorear comportamiento: Verificar que tu lógica funcione correctamente
  //Producción: En apps publicadas, ayudan a diagnosticar problemas reportados por usuarios
  
  //En resumen poner un Log es como preguntar si funciona esta parte de la funcion que estoy haciendo.
  //Esta llamada a esta funcionalidad funciona?, el codigo funciona hasta este punto?, el codigo se traba en este lugar?...
  
  //TODO: Y ES POR ESO QUE HAY DISTINTOS TIPOS DE LOGS SEGUN LA "SEVERIDAD" DEL ERROR
  
  //Para esto debemos saber que es un Log, como esta compuesto y como funciona (SU ANATOMIA) =
  //TODO: //¿Qué significa "Log"?
  //Log viene del inglés "logbook" (libro de registro o bitácora).
  //Históricamente, en los barcos se usaba un "ship's log" (bitácora del capitán) donde se registraban todos los eventos importantes del viaje: velocidad, rumbo, clima, problemas, etc.
  //En programación, un log es exactamente lo mismo: un registro de eventos que ocurren mientras tu programa se ejecuta.
  
  //TODO: Log.d(tag, msg) - ¿Qué son estos elementos ? =
  
  //TODO: Tag - hace referencia al Identificador, es decir la palabra clave que vamos a utlizar para identificar donde esta nuestro error.
  //Para mayor visibilidad en Logcat se suele escribir la palabra clave en MAYUSCULAS
  
  //TODO: MSG - Es el mensaje descriptivo, puede ser la accion que realiza esa funcionalidad O puede ser el error que puede ocacionar esa funcionalidad.
  //Log.d( tag = "IDENTIFICADOR DE ERROR", msg = "Email vacío, mostrando error")
  
  //TODO: Ahora que es esa letra que se usa despues del Log?, ( .d , .v, .i )??. para que funcionan cada una?
  //En Android hay 5 niveles principales de logs =
  
  //1. Log.v() - (.v) Verbose (Verboso)
  Log.v("MiTag", "Información muy detallada del flujo")
  //Se mostrara de color Gris.
  //Cuándo usarlo: Detalles exhaustivos, cada paso del código
  //Ejemplo: "Entrando a la función X", "Valor de i = 5 en iteración 3"
  //En producción: Se elimina, demasiado ruido
  
  //------------------------------------
  
  //2. Log.d() - (.d) Debug (Depuración)
  Log.d("MiTag", "Información útil para debugging")
  //Se mostrara de color Azul.
  //Cuándo usarlo: Información útil durante desarrollo.
  //Ejemplo: "Usuario cargado: ${usuario.nombre}", "Lista tiene 10 elementos".
  //En producción: Se puede eliminar con ProGuard/R8.
  
  //------------------------------------
  
  //3. Log.i() - (.i) Info (Información)
  Log.i("MiTag", "Información general del programa")
  //Se mostrara de color Verde.
  //Cuándo usarlo: Eventos significativos pero normales.
  //Ejemplo: "Usuario inició sesión", "Datos sincronizados correctamente".
  //En producción: Generalmente se mantiene.
  
  //------------------------------------
  
  //4. Log.w() - (.w) Warning (Advertencia)
  Log.w("MiTag", "Algo inesperado pero no crítico")
  //Se mostrara de color Naranja/Amarillo.
  //Cuándo usarlo: Situaciones anormales pero recuperables.
  //Ejemplo: "API lenta, tardó 5 segundos", "Formato de fecha incorrecto, usando default".
  //En producción: Definitivamente se mantiene.
  
  //------------------------------------
  
  //5. Log.e() - (.e) Error
  //TODO:  Log.e("MiTag", "Error grave!", excepcion)
  //Se mostrara de color Rojo.
  //Cuándo usarlo: Errores que afectan funcionalidad.
  //Ejemplo: "No se pudo conectar a la base de datos", "Crash al parsear JSON".
  //En producción: SIEMPRE se mantiene.
  
  //--------------------------------------------------------------
  
  //TODO: Ejemplo completos:
  
  class LoginViewModel : ViewModel() {
    private val TAG = "LoginViewModel" // Constante para el tag (Buena practica)
    
    fun iniciarSesion(email: String, password: String) {
      Log.d(TAG, "Iniciando sesión para: $email")
      
      if (email.isEmpty()) {
        Log.w(TAG, "Email vacío, mostrando error")
        return
      }
      
      try {
        /*val usuario = repository.login(email, password)*/
//        Log.i(TAG, "Sesión iniciada exitosamente para: ${usuario.nombre}")
      } catch (e: Exception) {
        Log.e(TAG, "Error al iniciar sesión", e)
      }
    }
  }
  
  //TODO: 1. Rastrear el flujo de una función:
//  fun procesarPedido(pedido: Pedido) {
//
//    val TAG = "LoginViewModel" // Constante para el tag (Buena practica)
//
//    Log.d("EL TAG", "=== Iniciando procesamiento de pedido ${pedido.id} ===")
//
//    Log.v(TAG, "Validando items del pedido")
//    validarItems(pedido.items)
//
//    Log.v(TAG, "Calculando total")
//    val total = calcularTotal(pedido)
//    Log.d(TAG, "Total calculado: $$total")
//
//    Log.v(TAG, "Guardando en base de datos")
//    guardarPedido(pedido)
//
//    Log.i(TAG, "Pedido ${pedido.id} procesado exitosamente")
//  }
  
  //------------------------------------
  
  //TODO: 2. Debugging de valores:
//  fun filtrarProductos(productos: List<Producto>, categoria: String) {
//
//    val TAG = "LoginViewModel" // Constante para el tag (Buena practica)
//
//    Log.d(TAG, "Filtrando ${productos.size} productos por categoría: $categoria")
//
//    val filtrados = productos.filter { it.categoria == categoria }
//
//    Log.d(TAG, "Productos después del filtro: ${filtrados.size}")
//    Log.v(TAG, "Productos filtrados: ${filtrados.map { it.nombre }}")
//
//    return filtrados
//  }
  
  //------------------------------------
  
  //TODO: 3. Manejo de errores:
  fun cargarDatos() {
    val TAG = "LoginViewModel" // Constante para el tag (Buena practica)
    try {
      Log.d(TAG, "Cargando datos del servidor")
//      val datos = api.obtenerDatos()
//      Log.i(TAG, "Datos cargados: ${datos.size} elementos")
    } catch (e: IOException) {
      Log.e(TAG, "Error de red al cargar datos", e)
//      mostrarErrorRed()
    } catch (e: Exception) {
      Log.e(TAG, "Error inesperado al cargar datos", e)
//      mostrarErrorGenerico()
    }
  }
  
  //------------------------------------
  
  //TODO: 4. Estados del ciclo de vida:
//  class MainActivity : ComponentActivity() {
//    private val TAG = "MainActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//      super.onCreate(savedInstanceState)
//      Log.d(TAG, "onCreate llamado")
//    }
//
//    override fun onStart() {
//      super.onStart()
//      Log.d(TAG, "onStart llamado")
//    }
//
//    override fun onResume() {
//      super.onResume()
//      Log.d(TAG, "onResume llamado - App visible para el usuario")
//    }
//
//    override fun onPause() {
//      super.onPause()
//      Log.d(TAG, "onPause llamado - App perdiendo foco")
//    }
//
//    override fun onDestroy() {
//      super.onDestroy()
//      Log.d(TAG, "onDestroy llamado - Activity siendo destruida")
//    }
//  }
  
  //--------------------------------------------------------------
  
  //TODO: Buenas prácticas
  //TODO: 1. Uso de constantes para tags:
//  class MiClase {
//    companion object {
//      private const val TAG = "MiClase" // ✅ Bueno
//    }
//
//    fun miMetodo() {
//      Log.d(TAG, "Mensaje")
//      // En lugar de:
//      // Log.d("MiClase", "Mensaje") // ❌ Evitar strings literales
//    }
//  }
  
  //------------------------------------
  
  //TODO: 2. Mensajes descriptivos:
  // ❌ Malo
//  Log.d(TAG, "Aquí")
//  Log.d(TAG, "Error")

// ✅ Bueno
//  Log.d(TAG, "Usuario presionó botón de login")
//  Log.e(TAG, "No se pudo guardar el pedido en la BD local")
  
  //------------------------------------
  
  //TODO: 3. Incluye contexto:
  // ❌ Malo
//  Log.d(TAG, "Total: $total")

// ✅ Bueno
//  Log.d(TAG, "Carrito - Total calculado: $$total para ${items.size} items")
  
  //------------------------------------
  
  //TODO: 4. Logs de excepciones:
  // ✅ Incluye la excepción completa
//  try {
//    algo()
//  } catch (e: Exception) {
//    Log.e(TAG, "Error al procesar datos", e) // El stacktrace completo se registra
//  }
  
}

@Composable
fun MostrarUsoDeLogs() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
//    EntenderLogs()
  }
}