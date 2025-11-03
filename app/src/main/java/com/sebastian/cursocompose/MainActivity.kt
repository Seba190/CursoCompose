package com.sebastian.cursocompose

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebastian.cursocompose.ui.theme.CursoComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoComposeTheme {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    SelectorNumerico()
                }
                }

        }
    }
}

@Composable
fun SelectorNumerico(){
    var valorActual by remember{ mutableIntStateOf(0) }
    BotonesSelector(valorActual=valorActual, presiona = {
        valorActual += it
    })
}

@Composable
fun BotonesSelector(valorActual: Int, presiona: (Int) -> Unit){
    Column {
        Button(onClick = {presiona(-1)}) {
            Text(text = "Restar!")
        }
        Text(text = "$valorActual", modifier = Modifier.padding(start = 35.dp),fontSize = 30.sp)
        Button(onClick = {presiona(+1)}) {
            Text(text = "Sumar!")
        }
    }
}