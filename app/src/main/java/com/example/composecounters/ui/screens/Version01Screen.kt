package com.example.composecounters.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val START_COUNT_DEFAULT = 0
// https://kotlinlang.org/docs/coding-conventions.html#property-names
const val INCREMENT_DEFAULT = 1

@Preview(showBackground = true)
@Composable
fun Version01Screen() {

    var cuenta1 by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }
    var cuenta2 by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }
    var cuentaG by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }

    var incremento1 by rememberSaveable { mutableStateOf(INCREMENT_DEFAULT) }
    var incremento2 by rememberSaveable { mutableStateOf(INCREMENT_DEFAULT) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { cuenta1 += incremento1; cuentaG += incremento1 }) {
                    Text(text = "Contador 1  ($cuenta1)")
                }
                Spacer(Modifier.width(10.dp))
                Text(text = cuenta1.toString())
                Spacer(Modifier.width(6.dp))
                Image(painter = painterResource(id = android.R.drawable.ic_menu_delete),
                    contentDescription = "Borrar",
                    Modifier.clickable { cuenta1 = 0 })
            }
            Row(
                Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "Incremento: ")
                BasicTextField(value = incremento1.toString(), onValueChange = {
                    incremento1 = it.toIntOrNull() ?: INCREMENT_DEFAULT
                    if (incremento1 > 99 || incremento1 < 1) incremento1 = INCREMENT_DEFAULT
                },
                    Modifier
                        .width(30.dp)
                        .height(28.dp), textStyle = LocalTextStyle.current.copy(
                    color = MaterialTheme.colors.onBackground, textAlign = TextAlign.End
                ), // (2)
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // (1)
                    decorationBox = {
                        DecorationBox(it)
                    })
            }
        }

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { cuenta2 += incremento2; cuentaG += incremento2 }) {
                    Text(text = "Contador 2  ($cuenta2)")
                }
                Spacer(Modifier.width(10.dp))
                Text(text = cuenta2.toString())
                Spacer(Modifier.width(6.dp))
                Image(painter = painterResource(id = android.R.drawable.ic_menu_delete),
                    contentDescription = "Borrar",
                    Modifier.clickable { cuenta2 = 0 })
            }
            Row(
                Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "Incremento: ")
                BasicTextField(value = incremento2.toString(), onValueChange = {
                    incremento2 = it.toIntOrNull() ?: INCREMENT_DEFAULT
                    if (incremento2 > 99 || incremento2 < 1) incremento2 = INCREMENT_DEFAULT
                },
                    Modifier
                        .width(30.dp)
                        .height(28.dp), textStyle = LocalTextStyle.current.copy(
                    color = MaterialTheme.colors.onBackground, textAlign = TextAlign.End
                ), // (2)
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // (1)
                    decorationBox = {
                        DecorationBox(it)
                    })
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Global")
            Spacer(Modifier.width(10.dp))
            Text(text = cuentaG.toString())
            Spacer(Modifier.width(6.dp))
            Image(painter = painterResource(id = android.R.drawable.ic_menu_delete),
                contentDescription = "Borrar",
                Modifier.clickable { cuentaG = 0 })
        }
    }
}

@Composable
fun DecorationBox(innerTextField: @Composable () -> Unit) {
    Row(
        Modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .border(
                width = .5.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(4.dp), verticalAlignment = Alignment.Bottom
    ) { innerTextField() }
}


/*
(1) https://stackoverflow.com/questions/66482473/how-to-set-the-inputtype-for-a-textfield-in-jetpack-compose
(2) https://stackoverflow.com/questions/68517136/jetpack-compose-align-input-text-in-textfield
 */

