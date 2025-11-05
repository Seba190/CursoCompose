package com.sebastian.cursocompose

import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastian.cursocompose.ui.theme.CursoComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          TextoConSlider()
            MenuTareas()
       }
    }
}

@Composable
fun TextoConSlider(){
    var fontSize by remember { mutableFloatStateOf(30f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center ,modifier = Modifier.height(100.dp) ){
            Text(text = "Sebastián", fontSize = fontSize.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(value = fontSize,
                valueRange = 25f..100f,
                onValueChange = {fontSize = it},
                modifier = Modifier
                    .weight(0.9f)
                    .padding(end = 16.dp)
                )
            Text(text = fontSize.toInt().toString(),
                modifier = Modifier.weight(0.1f))
        }
    }

}

@Composable
fun MenuTareas(){
    val context = LocalContext.current
    var expanded by remember {mutableStateOf(false)}
    val options = listOf("Bañarse", "Desayunar", "Trabajar", "Descansar", "Divertirse")

    Button(onClick = {expanded =! expanded}){
        Text(text = "Selecciona una tarea" )
    }
    DropdownMenu(
        expanded = true,
        onDismissRequest = {expanded = false}
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = { Text(text = option) },
                onClick = {
                    Toast.makeText(context, option, Toast.LENGTH_SHORT).show()
                } )}
    }
}

