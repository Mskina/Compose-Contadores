package com.example.composecounters.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun Version02Screen() {

    var cuenta1 by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }
    var cuenta2 by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }
    var cuentaG by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }

    var incremento1 by rememberSaveable { mutableStateOf(INCREMENT_DEFAULT) }
    var incremento2 by rememberSaveable { mutableStateOf(INCREMENT_DEFAULT) }


    val focusManager = LocalFocusManager.current

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        BloqueContador(
            buttonText = "Contador 1",
            cuenta = cuenta1,
            incremento = incremento1,
            onClick = {
                cuenta1 += incremento1
                cuentaG += incremento1
                focusManager.clearFocus()  // Para ocultar el teclado
            },
            setIncrement = { incremento1 = it },
            onResetCount = { cuenta1 = START_COUNT_DEFAULT }
        )
        BloqueContador(
            buttonText = "Contador 2",
            cuenta = cuenta2,
            incremento = incremento2,
            onClick = {
                cuenta2 += incremento2
                cuentaG += incremento2
            },
            setIncrement = { incremento2 = it },
            onResetCount = { cuenta2 = START_COUNT_DEFAULT }
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Global")
            Spacer(Modifier.width(10.dp))
            Text(text = cuentaG.toString())
            Spacer(Modifier.width(6.dp))
            Image(painter = painterResource(id = android.R.drawable.ic_menu_delete),
                contentDescription = "Borrar",
                Modifier.clickable { cuentaG = START_COUNT_DEFAULT })
        }
    }
}

@Composable
fun BloqueContador(
    buttonText: String,
    cuenta: Int,
    incremento: Int,
    onClick: () -> Unit,
    setIncrement: (Int) -> Unit,
    onResetCount: () -> Unit,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onClick) {
                Text(text = "$buttonText  ($cuenta)")
            }
            Spacer(Modifier.width(10.dp))
            Text(text = cuenta.toString())
            Spacer(Modifier.width(6.dp))
            Box(Modifier.clickable(onClick = onResetCount)) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_delete),
                    contentDescription = "Borrar"
                )
            }
        }
        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = "Incremento: ")
            BasicTextField(
                value = incremento.toString(),
                onValueChange = { setIncrement(incrementFromString(it)) },
                Modifier
                    .width(30.dp)
                    .height(28.dp),
                textStyle = LocalTextStyle.current.copy(
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.End
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                decorationBox = { DecorationBox(it) }
            )
        }
    }
}


fun incrementFromString(string: String) = string.toIntOrNull()
    ?.let { if (it > 99 || it < 1) INCREMENT_DEFAULT else it } // si no está en [1,99]
    ?: INCREMENT_DEFAULT // o si no es un int, devuelve incrementDefault


/*
 Esta versión tiene un gran problema de UX: si se quiere cambiar un número no se puede borrar, ya
 que dejar la cadena vacía introduce un 1 directamente (1). Se soluciona en la variante Ej05c.
 */