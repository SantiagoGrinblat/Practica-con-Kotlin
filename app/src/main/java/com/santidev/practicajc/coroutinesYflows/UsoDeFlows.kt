package com.santidev.practicajc.coroutinesYflows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

@Composable
fun UsoDeFlows() {
  
  //todo: ¿Qué son los Flows?
  //todo: Flow = Flujo de datos que puede ser procesado de manera sincrona
  //Bacimente seria, observamos un valor, y cada vez que ese valor cambia llamamos a una coroutine
  //No solo eso, si no que ademas, los flows son coroutinas que devuelve muchos valores.
  
  //EJEMPLO explicado =
  // Una coroutina comun devuelve solo un valor (una llamada a una API), devuelve unicamente un error O devuelve el valor de la llamada a la api.
  //En cambio con flow si podemos devolver multiples valores.
  //EJEMPLO =
  
  @Composable
  fun FlowDemo() {
    
    // Estado que se actualiza con los valores del Flow
    var valorActual by remember { mutableStateOf("Esperando...") }
    
    // LaunchedEffect se ejecuta cuando el Composable se crea
    LaunchedEffect(Unit) {
      flow { // Flow que emite valores
        emit(1) // Emite el valor 1
        delay(2000) // Espera 2 segundos
        emit(3) //Emite el siguiente valor
        delay(2000)
        emit(5)
      }.collect { valor ->
        // Actualiza el estado (y la UI) con cada valor
        valorActual = "El valor es $valor"
      }
    } //Y asi con cada uno de los elementos.
    //Esto en una coroutine normal, seria muy complicado de hacer, no se puede devolver tanto valores
    
    // Muestra el valor en pantalla
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = valorActual,
      )
    }
  }
  
  //TODO: ¿Que es collect?
  //collect = ESCUCHAR/OBSERVAR/RECIBIR
  //Es decir collect lo que hace es observar al flow y que el flow esta emition "valores" =
  //  emit(1)
  //  delay(2000)
  //  emit(3)
  //  delay(2000)
  //  emit(5)
  
  //collect lo que hace es obsevar que el flow emite 1, entonces que hace =
  //  collect { valor -> // Recibe el valor emitido (1) por el flow, se le asiga a esa "observacion" (emit) un valor para poder usarlo
  //    // Actualiza el estado (y la UI) con cada valor
  //    valorActual = "El valor es $valor" // Actualiza el estado (y la UI) con cada valor
  //  }
  
  //todo:Un ejemplo claro seria, por ejemplo, el telefono =
  // Persona 1 habla: "Hola, como estas?"
  // flow EMITE (transmite): "Hola, como estas?"
  // collect RECIBE (observa): "Hola, como estas?"
  // Persona 2 escucha: "Hola, como estas?"
  
  //------------------------------------
  
  //TODO: ¿Que es collectLatest?
  //Hace lo mismo que collect pero con una pequeña diferencia en caso que de mientras este cargando la coroutine a mostar, el valor de esta cambia, deja de emitir la que estaba por mostrar y carga la actualizada para mostrar esa
  
  //ejemplo =
  //  flow { // Flow que emite valores
  //    emit(1) // Emite el valor 1
  //    delay(2000) //tarda 2 segundo
  //    emit(3)
  //    delay(2000)
  //    emit(5)
  //  }.collectLatest { valor ->
  
  //    //va a intentar emitir el valor de (1), pero como tiene un delay de 3 segundo.
  //    //cuando intente emitir el valor 1, este ya paso y se carga el valor de 3
  //    //Esto pasa porque tarda mas en emitir el mensaje que en lo que flow emite el mensaje.
  //    //Por eso, cuando lo intente emitir ya va a ser tarde y va intentar emitir el siguiente valor que emite flow
  //    //Y asi consecutivamente  hasta poder emitir un valor que realmente pueda llegar (por tiempo en este caso) a mostrar
  
  //    delay(3000) // // tarda 3 segundos en mostrar el texto
  //    valorActual = "El valor es $valor" // texto a mostrar
  //  }
  
  //------------------------------------
  
  //TODO: Ahora vamos a ver varios tipos de Flows que hay =
  //todo: ColdFlow = No guarda estado. Cada vez que se colecciona, se ejecuta desde el principio. Solo emite valores si hay un colector
  //todo: HotFlow = Pueden emitir valores sin tener colectores y guardan el ultimo estado
  
  //Todo: Cold Flow =
  //No guarda estado, por lo tanto cada vez que se haga llamada al Flow, va a correr nuevamente de vuelta.
  //ejemplo =
  
  //Cold Flow
  val flow = flow {
    emit(1)
    emit(3)
    emit(5)
  }
  
  //Al no estar en kotlin comun, hay que hacer un pequeño cambio en la logica para ver los resultados
  @Composable
  fun ColdFlow() {
    
    var valor by remember { mutableStateOf("Valor inicial") }
    
    // LaunchedEffect para ejecutar coroutines.
    //collect simple, para mostrar que aun se puede ver el resultado
    LaunchedEffect(Unit) {
      flow.collect { numero ->
        valor = "Valor emitido: $numero"
      }
    }
    
    // LaunchedEffect para ejecutar coroutines
    //collectLatest para demostrar que no importa cual sea la coroutine, se vuelve a ejecutar todo de vueta
    LaunchedEffect(Unit) {
      flow.collectLatest { nuevo ->
        valor = "Nuevo Valor emitido: $nuevo"
      }
    }
    
    // UI
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = valor)
      Text(text = valor)
    }
  }
  //Datos a tener en cuenta, los ColdFlows nunca son llamados si no tenes una coroutine que los colecte, (collect, collectLatest)
  
  //TODO:Cuando lo revisamos a detalle dentro del logcast, lo que vemos es esto =
  //Collect: Iniciando | //Flow iniciado
  //Collect recibió: 1 | //Emitido: 1
  //Collect recibió: 3 | //Emitido: 3
  //Collect recibió: 5 | //Emitido: 5
  //Collect: Terminado | Flow completado
  
  //------------------------------------
  
  //TODO: Hot Flow =
  //HotFlow, por defecto tiene lo contrario a lo que hace ColdFlow, este si puede guardar valor y puede emitir valores por mas que no sean llamadaos en un collect/collectLatest
  //Este sirve mucho ya que con hotflow, siempre vamos a obtener el ultimo valor (el que guardo el estado), no va a recorrer y mostrar todos los estados como el otro.
  
  //ejemplo =
  //  val hotFlow = MutableStateFlow(1)
  
  //  @Composable
  //  fun HotFlow() {
  //    var valor by remember { mutableStateOf("Esperando...") }
  
  //    LaunchedEffect(Unit) {
  //      println("Collect: Iniciando")
  
  //      hotFlow.emit(3)
  //      delay(2000)
  //      hotFlow.emit(5)
  
  //      println("Collect: Terminado")
  
  //      hotFlow.collect {
  //        valor = "Valor emitido: $it"
  //      }
  //    }
  
  //    Column(
  //      modifier = Modifier.fillMaxSize(),
  //      horizontalAlignment = Alignment.CenterHorizontally,
  //      verticalArrangement = Arrangement.Center
  //    ) {
  //      Text(valor, fontSize = 24.sp)
  //    }
  //  }
  
  //todo: En este caso solo se mostrara el valor 5, ya que este recorre la coroutine, pero solamente muestra el ultimo elemento.
  //Y esto es incluso en emiciones posteriores al collect como pasa con el
  
}

val hotFlow = MutableStateFlow(mutableStateListOf(1, 2, 3, 4))

@Composable
fun HotFlow() {
  
  val lista by hotFlow.collectAsState()
  
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    LaunchedEffect(Unit) {
      hotFlow.collect {
        println("Valor emitido: $it")
      }
      delay(3000)
    }
    
    LaunchedEffect(Unit) {
      println("Iniciando la nueva de update... ")
      delay(2000)
      
      println("update1")
      hotFlow.update {
        mutableStateListOf(1, 2, 3, 4, 89)
      }
      
      delay(2000)
      
      println("update2")
      hotFlow.update {
        mutableStateListOf(89, 12904)
      }
    }
    Text(text = "Valor emitido:")
    
    lista.forEach { numeros ->
      Text(text = "$numeros")
    }
  }
}


@Composable
fun MostrarUsoDeFlows() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    HotFlow()
    UsoDeFlows()
  }
}