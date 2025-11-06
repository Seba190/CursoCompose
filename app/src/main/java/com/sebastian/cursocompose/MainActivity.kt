package com.sebastian.cursocompose

import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastian.cursocompose.ui.theme.CursoComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TransitionInfinite()
        }
    }

@Composable
fun TransitionInfinite(){
    val infiniteTransition = rememberInfiniteTransition()
    val heartSize by infiniteTransition.animateFloat(
        initialValue = 100.0f,
        targetValue = 250.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, delayMillis = 100, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        ))

    Image(painter = painterResource(id = R.drawable.img_2), contentDescription = "corazon", modifier = Modifier.size(heartSize.dp))
}

@Composable
fun TransitionAnimation(){
    var isAnimated by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isAnimated, label = "transition")

    val rocketOffset by transition.animateOffset(transitionSpec = {if(this.targetState) tween(1000) else tween(1500)},
        label = "rocket offset") { animated -> if (animated) Offset(200f, 0f) else Offset(200f, 500f) }

    val rocketSize by transition.animateDp(transitionSpec = {tween(1000)},""){
        animated -> if(animated) 50.dp else 250.dp

    }
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "rocket",
            modifier = Modifier
                .size(rocketSize)
                .alpha(1.0f)
                .offset(rocketOffset.x.dp, rocketOffset.y.dp)
        )

        Button(onClick = { isAnimated = !isAnimated }) {
            Text(text = if (isAnimated) "Aterrizar" else "Despegar")
        }
    }

}

    @Composable
    fun AnimatedFloatAsState(){
        var isRotated by rememberSaveable {mutableStateOf(false) }
        val rotationAngle by animateFloatAsState(targetValue = if(isRotated) 1080f else 0f,
            animationSpec = tween(5500))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            Image(painter = painterResource(id = R.drawable.img),contentDescription = "ventilador",modifier = Modifier
                .padding(top = 50.dp)
                .rotate(rotationAngle)
                .size(150.dp))
          Button(onClick = {isRotated = !isRotated}, modifier = Modifier.padding(top = 50.dp)
              .width(200.dp)) {
              Text(text = "Rotar Ventilador!")
          }
        }

    }
    @Composable
    fun ImagenCircular(imageSize: Dp){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "imagen ciruclar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
                .border(5.dp, Color.LightGray, CircleShape)
        )
    }
    @Composable
    fun AnimarDpAsState(){
       val isNeedExpansion = rememberSaveable {mutableStateOf(false) }
        val animatedSizeDp: Dp by animateDpAsState(targetValue = if(isNeedExpansion.value) 350.dp else 100.dp)
       Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
           ImagenCircular(imageSize = animatedSizeDp)
           Button(onClick = {isNeedExpansion.value = !isNeedExpansion.value},
               modifier = Modifier.padding(top = 50.dp)
                   .width(300.dp)) {
               Text(text = "Animate DP as State!")
           }
       }

    }

@Composable
fun AnimableEjemplo(){
    var isAnimated by remember { mutableStateOf(false) }
    val color = remember { Animatable(Color.DarkGray) }

    LaunchedEffect(isAnimated) {
        color.animateTo(if(isAnimated) Color.Green else Color.Red,
            animationSpec = tween(3000))
    }

    Box(Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color.value)){
        Button(onClick = {isAnimated = !isAnimated}, modifier = Modifier.padding(10.dp)) {
             Text("Animar el color!")
        }
    }

}

    @Composable
    fun TarjetaBasica() {
        Card() {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.LightGray, shape = CircleShape)
                            .size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        //Miniatura
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    Column(Modifier.fillMaxWidth()) {
                        //Titulo
                        Text(text = "SebaAV", style = MaterialTheme.typography.titleLarge)
                        //Subtitulo
                        Text(text = "Canal de Youtube", style = MaterialTheme.typography.bodySmall)

                    }
                }
                //Multimedia
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Multimedia de la tarjeta",
                    modifier = Modifier
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                        .height(194.dp)
                )
                Row(Modifier.padding(all = 18.dp)) {
                    Text(
                        text = "Soy Sebastián Alexis Valenzuela Henríquez, esto es un gran texto",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    Modifier.padding(horizontal = 8.dp)
                        .fillMaxWidth()
                ) {
                    Row(modifier = Modifier.align(Alignment.BottomStart)) {
                        TextButton(onClick = {}) {
                            Text(text = "Acción 1")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(onClick = {}) {
                            Text(text = "Acción 2")
                        }
                    }
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Share, contentDescription = null)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun TextoConSlider() {
        var fontSize by remember { mutableFloatStateOf(30f) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.height(100.dp)) {
                Text(text = "Sebastián", fontSize = fontSize.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Slider(
                    value = fontSize,
                    valueRange = 25f..100f,
                    onValueChange = { fontSize = it },
                    modifier = Modifier
                        .weight(0.9f)
                        .padding(end = 16.dp)
                )
                Text(
                    text = fontSize.toInt().toString(),
                    modifier = Modifier.weight(0.1f)
                )
            }
        }

    }

    @Composable
    fun MenuTareas() {
        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }
        val options = listOf("Bañarse", "Desayunar", "Trabajar", "Descansar", "Divertirse")

        Button(onClick = { expanded = !expanded }) {
            Text(text = "Selecciona una tarea")
        }
        DropdownMenu(
            expanded = true,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        Toast.makeText(context, option, Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}

