package com.sebastian.cursocompose

import android.R
import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
          Column(modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally) {
              val checked = remember { mutableStateOf(true) }
              val isOn = remember { mutableStateOf(true) }
              FloatingActionButton(onClick = {Toast.makeText(applicationContext, "Click en el floating button!", Toast.LENGTH_SHORT)
                  .show()},
                  modifier = Modifier.size(40.dp)) {
                  Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
              }
              Switch(
                  checked = isOn.value,
                  onCheckedChange = { isOn.value = it
                      Toast.makeText(applicationContext, "Cambio el SWITCH", Toast.LENGTH_SHORT)
                          .show()},
                  colors = SwitchColors(
                      checkedBorderColor = Color.Blue,
                      checkedThumbColor = Color.White,
                      checkedTrackColor = Color.Red,
                      checkedIconColor = Color.Black,
                      uncheckedThumbColor = Color.Green,
                      uncheckedTrackColor = Color.Green,
                      uncheckedBorderColor = Color.Green,
                      uncheckedIconColor = Color.Green,
                      disabledCheckedThumbColor = Color.Green,
                      disabledCheckedTrackColor = Color.Green,
                      disabledCheckedBorderColor = Color.Green,
                      disabledCheckedIconColor = Color.Green,
                      disabledUncheckedThumbColor = Color.Green,
                      disabledUncheckedTrackColor = Color.Green,
                      disabledUncheckedBorderColor = Color.Green,
                      disabledUncheckedIconColor = Color.Green
                  )
              )
              Checkbox(checked = checked.value,
                  onCheckedChange = {checked.value = it})
              var isSelected = remember { mutableStateOf(false) }
              RadioButton(
                  selected = isSelected.value,
                  onClick = {
                      Toast.makeText(applicationContext, "Click en Radio Button", Toast.LENGTH_LONG)
                          .show()
                      isSelected.value = !isSelected.value
                  }
              )
              Button(onClick = {
                  if(checked.value) {
                      Toast.makeText(applicationContext, "Esta seleccionado", Toast.LENGTH_LONG)
                          .show()
                  } else {
                      Toast.makeText(applicationContext, "NO esta seleccionado", Toast.LENGTH_LONG)
                          .show()

                  }
              }) {
                  Text(text = "Evaluar")
              }
          }
        }
    }
}