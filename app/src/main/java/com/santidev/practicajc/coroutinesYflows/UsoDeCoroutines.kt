package com.santidev.practicajc.coroutinesYflows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UsoDeCoroutines() {
  
  //TODO: ENTENDER QUE ES UNA COROUTINE CUANDO USARLO, SU FORMA, Y DE QUE MODO
  //TODO: ¿Qué son las Coroutines?
  //Las corrutinas sirven para ejecutar "cosas" (funciones, carga de datos... ETC) en segundo plano.
  //De esta forma mientras estas "cosas" cargan, la app no se traba, y se puede seguir usando, cuando estas "cosas" terminan de cargar, simplemente se muestra
  //TODO: ejemplo practico y visual =
  
  // ❌ MALO - Esto congela tu app
  //  fun cargarDatos() {
  //    val datos = descargarDeInternet() // Tarda 3 segundos
  //    durante estos 3 segundo la app de muestra en pantalla blanca sin que se pueda usar para nada, simplemente esperar
  //    mostrarDatos(datos) // la app ya muestra los datos y la UI se puede usar
  //  }
  //  // ✅ BUENO - Con Coroutines
  //  suspend fun cargarDatos() {  // En segundo plano carga los datos que tardan 3 segundos
  //    val datos = descargarDeInternet() // los datos se estan cargando.
  //    mostrarDatos(datos)  //pero la app muestra contenido y se puede utilizar y cuando termine los 3 segundos se muestran los datos pero la app nunca dejo de funiconar
  //  }
  
  //------------------------------------
  
  //TODO: ¿Qué es suspend?
  
  // suspend es una palabra clave que marca que una funcion PUEDE pausarse, DURANTE SU EJECUCION (NO ANTES).
  // La funcion suspend NO se ejecuta automaticamente ni esta esperando.
  // SOLO se ejecuta cuando es llamada DENTRO de una coroutine (launch, async, etc.)
  // Una vez que empieza a ejecutarse, PUEDE pausarse en ciertos puntos
  // (como delay, llamadas a API, etc.) sin bloquear el uso normal de la app.
  
  //------------------------------------
  
  //TODO: ¿Como hago el llamado dentro de una corrutina a esta funcion suspendida?
  //Para esto tenemos varios mecanismos los mas comunes y faciles de usar son = //TODO:los Constructores de coroutines
  //Estos constructores de coroutines son funciones especiales que crean y lanzan coroutines. Son los unicos que pueden ejecutar funciones suspend.
  
  //TODO: hay 3 constructores principales =
  //TODO: launch{} → Fire and forget (lanzar y olvidar)
  //TODO: async → Retorna un resultado
  //TODO: withContext → Cambia de hilo temporalmente
  
  //TODO: 1. Launch =
  //Lanza una coroutine que NO retorna ningún valor.
  //Es para ejecutar codigo que hace algo pero no necesitas su resultado.
  //TODO: EJEMPLO VISUAL =
  suspend fun descargarDatos(): String {
    delay(2000) // Simula espera de red
    return "Datos descargados"
  } // funcion suspendida esperando que la llamen para ser ejecutada
  
  class MiViewModel : ViewModel() { // clase que hereda de viewModel
    fun cargarDatos() { // funcion para cargar los datos
      viewModelScope.launch { // ← Scope, este funciona con "periodos de vida", cuando se cierra la app este se destruye, evitando sobre carga de la app
        val datos =
          descargarDatos() // variable que espera los datos de la funcion suspendida a la cual llama para ejecutar
        /*_datos.value = datos*/ // recibe los valores de los datos de la funcion suspendida y los almacena.
      }
    } // Esto es un Builders - Constructores de coroutines, solo la funcion, NO la clase total.
  }
  
  //TODO: SCOPES - Alcance de vida de las coroutines
// Los scopes determinan CUANDO se cancelan automaticamente las coroutines.
  
  //TODO: viewModelScope:
  // CoroutineScope ligado al ciclo de vida del ViewModel.
  // Se cancela automaticamente en onCleared() (cuando se destruye el ViewModel).
  // Ideal para logica de negocio, llamadas a red y base de datos.
  // Sobrevive a cambios de configuración (rotación de pantalla).
  // Requiere: implementation("androidx.lifecycle:lifecycle-viewmodel-ktx")
  
  // TODO: Ejemplo:
  // es el ejemplo de antes, esta detallado arriba
  // fun cargarDatos() {
  //     viewModelScope.launch {
  //         val datos = repository.obtenerDatos()
  //         _datos.value = datos
  //     }
  // }

//------------------------------------
  
  //TODO: lifecycleScope:
  // CoroutineScope ligado al ciclo de vida de una Activity o Fragment.
  // Se cancela en onDestroy() (cuando se destruye la Activity/Fragment = UI).
  // NO sobrevive a rotaciones de pantalla (se cancela y recrea toda la UI).
  // Ideal para tareas relacionadas con la UI actual (observar Flows, animaciones).
  // Requiere: implementation("androidx.lifecycle:lifecycle-runtime-ktx")
  
  // TODO: Ejemplo:
  // override fun onCreate(savedInstanceState: Bundle?) { // Se sobreescribe (override) el metodo onCreate de una Activity, se usa savedInstanceState para restaurar el estado si la Activity fue recreada. ejemplo: (rotacion de pantalla)
  //     super.onCreate(savedInstanceState) // Llama a la implementacion de onCreate de la clase padre (ComponentActivity/AppCompatActivity).
  //     lifecycleScope.launch { // Se lanza la coroutine dentro del scope de la Activity
  //         viewModel.datos.collect { datosRecuperados ->
  // Observa el Flow "datos" del ViewModel.
  // - viewModel.datos: Es un Flow que emite valores
  // - collect: Escucha continuamente nuevos valores
  // - datosRecuperados: Parámetro lambda con el valor emitido actual
  //    Cada vez que viewModel.datos emita un nuevo valor,
  //    este bloque se ejecuta con ese nuevo valor.
  //             actualizarUI(datos) // Se llama a la funcion actualizarUI y con esta "actualizacion de UI" trae los datos que estamos recuperando en el viewModel
  //         }
  //     }
  // }

//------------------------------------
  
  //TODO: GlobalScope:
  // ⚠️ EVITAR su uso en casi todos los casos.
  // No esta ligado a ningun ciclo de vida.
  // Vive mientras el proceso de la app este activo.
  // Puede provocar memory leaks y trabajos innecesarios en background.
  // Solo usar si REALMENTE necesitas algo que sobreviva a toda la app.
  
  // ❌ Mal uso:
  // GlobalScope.launch { descargarDatos() }  // Se ejecuta aunque cierres la pantalla
  //
  // ✅ Casi nunca hay razon valida para usarlo

//------------------------------------
  
  //TODO: CoroutineScope():
  // Scope manual/personalizado que controlas.
  // Requiere cancelar explicitamente con job.cancel().
  // Util solo en casos especificos fuera de Android (librerias, tests, backend).
  
  // TODO: Ejemplo:
  // class MiClase {  // Clase personalizada
  //     private val scope = CoroutineScope(Dispatchers.Default)
  // Crea un CoroutineScope (contenedor) que puede lanzar multiples coroutines.
  //Dispatchers.Default: Especifica en que tipo de hilo se ejecutaran.
  //
  //     fun iniciar() { // Función que lanza una coroutine dentro del scope personalizado.
  //         scope.launch {
  // aca va el trabajo asincrono (ej: procesamiento de datos, calculos)
  // Se ejecuta en Dispatchers.Default (hilo de fondo)
  //         }
  //     }
  //
  //     fun detener() { // // Funcion que cancela TODAS las coroutines activas en este scope.
  //         scope.cancel()
  // IMPORTANTE: cancelar manualmente
  //  Si no llamas esto, las coroutines seguiran ejecutandose
  // incluso despues de que ya no necesites esta clase (memory leak).
  //     }
  // }

//------------------------------------
  
  //TODO: REGLA DE ORO - ¿Qué scope usar?
  
  // ViewModel (datos/logica)     → viewModelScope
  // Activity/Fragment (UI)       → lifecycleScope
  // App completa                 → ❌ GlobalScope (evitar)
  // Clase personalizada          → CoroutineScope() + cancel manual
  // Composable                   → rememberCoroutineScope()

//------------------------------------
  
  //TODO: COMPARACIÓN DE SOBREVIVENCIA:
  
  // Evento: Usuario rota la pantalla
  // ├─ viewModelScope     → ✅ SOBREVIVE (ViewModel no se destruye)
  // ├─ lifecycleScope     → ❌ SE CANCELA (Activity/Fragment se recrea)
  // └─ GlobalScope        → ✅ SOBREVIVE (pero sigue corriendo innecesariamente ⚠️)
  
  // Evento: Usuario navega atrás (cierra pantalla)
  // ├─ viewModelScope     → ✅ SE CANCELA (cuando BackStack limpia el ViewModel)
  // ├─ lifecycleScope     → ✅ SE CANCELA (inmediatamente)
  // └─ GlobalScope        → ❌ SIGUE CORRIENDO (memory leak ⚠️)

//------------------------------------

//TODO: SCOPE ADICIONAL PARA COMPOSE:

//TODO: rememberCoroutineScope():
// Scope ligado al ciclo de vida de un Composable.
// Se cancela cuando el Composable sale de composición.
// Ideal para eventos de UI en Compose (clicks, gestos).
//
// Ejemplo:
// @Composable
// fun MiPantalla() {
//     val scope = rememberCoroutineScope()
//
//     Button(onClick = {
//         scope.launch {
//             // Animar, llamar suspend fun, etc.
//             animarAlgo()
//         }
//     }) {
//         Text("Click")
//     }
// }
  
  //--------------------------------------------------------------
  
  //TODO: 2. async/await
  //TODO: ¿Qué es async?
  //async = es un coroutine builder que lanza una coroutine y retorna un resultado futuro (Deferred).
  
  //ejemplo practico =
  /*val deferred: Deferred<String> = viewModelScope.async(
    context = Dispatchers.IO,           // En que hilo ejecutar (opcional)
    start = CoroutineStart.DEFAULT,     // Cuando iniciar (opcional)
  ) {*/
  // Codigo que retorna un valor
//    descargarDatos()
  "Resultado"  // ← Este es el valor que retorna
  //}

// Esperar el resultado:
//  val resultado: String = deferred.await()
  
  //TODO: parte importante
//  val deferred: Deferred<String> = viewModelScope.async(){}
  //TODO: deferred: Deferred<String> = Objeto "Promesa" de un resultado futuro de tipo string
  //TODO: viewModelScope. = Scope donde vive la coroutine
  //TODO: async { ... } = Builder que crea la coroutine
  
  //Es muy complicado entender esto de referred pero un ejemplo muy bueno es este =
  //Deferred<T> - La "promesa" del resultado
  //Deferred es como un ticket de reclamación:
  //Pides comida en el restaurante
  //  val ticket: Deferred<Pizza> = async {
  //    cocinarPizza()  // Tarda 20 minutos
  //  }
  
  //Haces otras cosas mientras...
  //hacerOtrasCosas()
  
  //Cuando necesitas la pizza:
  //  val pizza: Pizza = ticket.await()  // Reclamas con el ticket
  //  comerPizza(pizza)
  
  //------------------------------------
  
  //TODO: Propiedades de Deferred =
//  val deferred = async { delay(1000); "Resultado" }

// Verificar si termino:
//  if (deferred.isCompleted) {
//    Text("Ya termino")
//  }

// Verificar si esta activa:
//  if (deferred.isActive) {
//    Text("Todavia trabajando")
//  }

// Cancelar:
//  deferred.cancel()

// Esperar el resultado:
//  val resultado = deferred.await()  // Bloquea hasta tener el resultado
  
  //------------------------------------
  
  //TODO:await() - Esperar el resultado
  //TODO: await() es una funcion suspend que espera a que la coroutine termine y retorna su resultado.
  
  //Características de await():
  //TODO: Es suspend - Solo se puede llamar desde coroutines
  //TODO: Espera el resultado - Se pausa hasta que async termine
  //TODO: Retorna el valor - Devuelve lo que retorno el bloque async
  //TODO: Propaga excepciones - Si async falla, await() lanza la excepcion
  
  //Entonces resumiendo un poco, async es bueno para hacer varias tareas al mismo tiempo, y tener un retorno "seguro" de que hagas lo que hagas lo tenes que tener controlado, porque si no falla.
  //Es bueno para tener multiples pedidos al mismo tiempo un ejemplo =
  suspend fun cargarDatos() {
    //val usuario = async { descargarUsuario() } //Con el async puedo pedir una carga de datos de los usuarios
    //val productos = async { descargarProductos() } // Al mismo tiempo que hace la carga de usuarios, hace la de productos.
    //val pedidos = async { descargarPedidos() } // y tambien la cantidad de pedidos
    //Ya que los hace todos al mismo tiempo sin que sea secuencial. (primero uno, despues otro y asi)
    
    // Esperar todos
    //Esto es bueno porque nos "obliga" a tener controlado tanto el pedido que hacemos como la entrega de ese pedido
    //val usuarioRetorno = usuario.await()
    //val productosRetorno = productos.await()
    //val pedidosRetorno = pedidos.await()
    // Total: 2 segundos
    // Mientras que con la forma secuencial serian 2 segundos por carga de datos que debe realizar,
    // dandonos un total de 6 segundos sin el async
  }
  
  //TODO: async/await - RESUMEN FINAL
  
  // async es bueno para:
  // 1. Hacer VARIAS tareas AL MISMO TIEMPO (en paralelo)
  // 2. Tener un RETORNO GARANTIZADO de cada tarea
  // 3. CONTROL OBLIGATORIO - debes usar await() para obtener resultados
  //
  // Ventaja principal:
  // Ejecuta TODO JUNTO (paralelo) y NO de forma SECUENCIAL (uno tras otro)
  
  // Ejemplo:
  // Sin async:  Tarea1 → Tarea2 → Tarea3  (6 segundos)
  // Con async:  Tarea1 ┐
  //             Tarea2 ├─ Al mismo tiempo (2 segundos)
  //             Tarea3 ┘
  
  // Cuando usar:
  // ✅ Multiples descargas de API
  // ✅ Procesar varios archivos
  // ✅ Consultar varias bases de datos
  // ✅ Cualquier tarea donde puedas hacer VARIAS COSAS A LA VEZ
  //
  // Cuando NO usar:
  // ❌ Una sola tarea (usa suspend fun normal)
  // ❌ Tareas dependientes (ej: necesitas resultado de A para hacer B)
  // ❌ No necesitas el resultado (usa launch)
  
  //--------------------------------------------------------------
  
  //TODO: Dispachers - Hilos de ejecucion
  //TODO: ¿Qué son los Dispatchers?
  //Los Dispachers determinan en que hilo/s se ejecutara la coroutine
  
  //ejemplo =
  //launch(Dispatchers.IO) {
  //     └──────┬──────┘
  //            └─ "Ejecuta esto en hilos de entrada/salida"
  //}
  
  //TODO: HAY 4 TIPOS DE DISPACHERS PRINCIPALES =
  //Dispatchers.Main      → Hilo principal (UI)
  //Dispatchers.IO        → Operaciones de entrada/salida (red, archivos, BD)
  //Dispatchers.Default   → Procesamiento CPU-intensivo (cálculos, algoritmos)
  //Dispatchers.Unconfined → Sin restricción (rara vez se usa)
  
  //------------------------------------
  
  //TODO: 1. Dispachers.Main =
  //El hilo principal de Android, también llamado UI thread. Es donde se actualiza la interfaz de usuario.
  
  //¿Que es exactamente el “hilo principal”?
  //Es un unico hilo en que:
  //Maneja el render de la UI
  //Procesa inputs (toques, clicks, scroll)
  //Ejecuta callbacks del sistema
  //⚠️ Si lo bloqueás → pantalla congelada / ANR
  
  //Ejemplo =
//  viewModelScope.launch(Dispatchers.Main) {
  // Codigo que se ejecuta en el hilo principal
//    textView.text = "Hola"  // ✅ Actualizar UI
//  }
  
  //TODO: Cuando usar y cuando NO Main en Dispachers =
//  ✅ PUEDES hacer en Main:
//  Actualizar UI (setText, setImageBitmap, etc.)
//  Navegacion entre pantallas
//  Mostrar/ocultar elementos
//  Animaciones

//  ❌ NO PUEDES hacer en Main:
//  Llamadas de red (API, descargas)
//  Operaciones de base de datos
//  Lectura/escritura de archivos
//  Procesamiento pesado (algoritmos, imágenes)
  
  //TODO: ¿Por qué? Porque si haces operaciones lentas en Main, la UI se congela (ANR - Application Not Responding).
  
  //Un ejemplo mas completo =
  //  class MiViewModel : ViewModel() {
  
  //    fun cargarDatos() {
  //      viewModelScope.launch(Dispatchers.Main) {
  //        // Estamos en Main
  
  //        mostrarCargando(true)  // ✅ OK - Actualizar UI
  
  //        val datos = database.query()  // ❌ MAL - Esto congelaría la UI
  
  //        // ✅ BIEN - Cambiar a IO para operación pesada
  //        val datos = withContext(Dispatchers.IO) {
  //          database.query()  // Se ejecuta en hilo IO
  //        }
  //        Vuelve automáticamente a Main
  
  //        actualizarUI(datos)    // ✅ OK - Actualizar UI
  //        mostrarCargando(false) // ✅ OK - Actualizar UI
  //      }
  //    }
  //  }
  
  //------------------------------------
  
  //TODO: Dispachers.IO =
  //Pool de hilos optimizado para operaciones de entrada/salida (Input/Output).
  
  //viewModelScope.launch(Dispatchers.IO) {
  // Operaciones de red, archivos, base de datos
  //}
  
  //TODO: Cuando usar IO? :
  //todo: 1 llamadas a RED/API =
  //withContext(Dispatchers.IO) {
  //val datos = api.obtenerDatos()
  //}
  
  //todo: 2 Base de datos (Room, SQLite) =
  // withContext(Dispatchers.IO) {
  //   database.insertUser(user)
  //   database.getAllUsers()
  // }
  
  //todo: 3 Lectura/escritura de archivos =
  // withContext(Dispatchers.IO) {
  //   File("datos.txt").writeText("contenido")
  //   File("datos.txt").readText()
  // }
  
  //todo: 4 Operaciones de red con sockets =
  // withContext(Dispatchers.IO) {
  //   socket.connect()
  //   socket.read()
  // }
  
  //todo: 5 SharedPreferences =
  // withContext(Dispatchers.IO) {
  //   preferences.edit().putString("key", "value").apply()
  // }
  
  //todo: ejemplos completos =
  
  //  class UsuarioRepository(
  //    private val api: ApiService,
  //    private val database: UserDao
  //  ) {
  
  //    // Todas estas funciones usan IO
  //    suspend fun obtenerUsuarioRemoto(id: String): Usuario {
  //      return withContext(Dispatchers.IO) {
  //        // Llamada de red
  //        api.getUser(id)
  //      }
  //    }
  
  //    suspend fun guardarUsuarioLocal(usuario: Usuario) {
  //      withContext(Dispatchers.IO) {
  //        // Base de datos
  //        database.insertUser(usuario)
  //      }
  //    }
  
  //    suspend fun obtenerUsuarioLocal(id: String): Usuario? {
  //      return withContext(Dispatchers.IO) {
  //        // Base de datos
  //        database.getUserById(id)
  //      }
  //    }
  
  //    suspend fun sincronizarUsuario(id: String): Usuario {
  //      return withContext(Dispatchers.IO) {
  //        // 1. Descargar de API
  //        val usuarioRemoto = api.getUser(id)
  
  //        // 2. Guardar en BD local
  //        database.insertUser(usuarioRemoto)
  
  //        // 3. Retornar
  //        usuarioRemoto
  //      }
  //    }
  //  }
  
  //--------------------------------------------------------------
  
}

@Composable
fun MostrarUsoDeCoroutines() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UsoDeCoroutines()
  }
}