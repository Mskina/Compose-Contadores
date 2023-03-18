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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composecounters.R


@Composable
fun Version03Screen() {

    var cuenta1 by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }
    var cuenta2 by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }
    var cuentaG by rememberSaveable { mutableStateOf(START_COUNT_DEFAULT) }

    var incremento1: Int? by rememberSaveable { mutableStateOf(INCREMENT_DEFAULT) }  // (1)
    var incremento2: Int? by rememberSaveable { mutableStateOf(INCREMENT_DEFAULT) }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        BloqueContadorC(buttonText = stringResource(R.string.count1),
            cuenta = cuenta1,
            incremento = incremento1,
            onClick = {
                cuenta1 += incremento1!! // (2)
                cuentaG += incremento1!!
            },
            setIncrement = { incremento1 = it },
            onResetCount = { cuenta1 = 0 }
        )
        BloqueContadorC(buttonText = stringResource(R.string.count2),
            cuenta = cuenta2,
            incremento = incremento2,
            onClick = {
                cuenta2 += incremento2!!
                cuentaG += incremento2!!
            },
            setIncrement = { incremento2 = it },
            onResetCount = { cuenta2 = 0 }
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.global))
            Spacer(Modifier.width(10.dp))
            Text(text = cuentaG.toString())
            Spacer(Modifier.width(6.dp))
            Image(painter = painterResource(id = android.R.drawable.ic_menu_delete),
                contentDescription = stringResource(R.string.reset),
                Modifier.clickable { cuentaG = 0 })
        }
    }
}

@Composable
fun BloqueContadorC(
    buttonText: String,
    cuenta: Int,
    incremento: Int?,
    onClick: () -> Unit,
    setIncrement: (Int?) -> Unit,
    onResetCount: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    onClick()
                    focusManager.clearFocus() // Esconde el teclado
                }, enabled = (incremento != null)
            ) { Text(text = "$buttonText  ($cuenta)") }
            Spacer(Modifier.width(10.dp))
            Text(text = cuenta.toString())
            Spacer(Modifier.width(6.dp))
            Box(Modifier.clickable(onClick = onResetCount)) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_delete),
                    contentDescription = stringResource(R.string.reset)
                )
            }
        }
        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = stringResource(R.string.increment))
            BasicTextField(
                value = incremento?.toString() ?: "",
                onValueChange = { setIncrement(incrementFromStringOrNull(it)) },
                Modifier
                    .width(30.dp)
                    .height(28.dp),
                textStyle = LocalTextStyle.current.copy(
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.End
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                decorationBox = {
                    DecorationBoxC(it, incremento == null)
                }
            )
        }
    }
}

fun incrementFromStringOrNull(string: String) = string.toIntOrNull()
    ?.let { if (it > 99 || it < 1) null else it } // si no está en [1,99] (ahora devuelve null)


@Composable
fun DecorationBoxC(innerTextField: @Composable () -> Unit, isError: Boolean) {
    Row(
        Modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .border(
                width = if (isError) 2.dp else .5.dp,
                color = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(4.dp),
        verticalAlignment = Alignment.Bottom
    )
    { innerTextField() }
}

/*

(1) Declaro el incremento nullable para utilizar incremento==null como indicador del estado en que,
estando el TextField de incremento vacío, se desactiva el bottón. El incremento se pone a null
cuando el TextField está vacío y con ello se desactiva el botón. Se soluciona
así el problema de la versión previa, que no permitía dejar vacía la caja para poder introducir
un nuevo número.

(2) Uso la llamada non-null asserted (!!). De este modo le garantizamos al compilador que el valor
en ese punto no será null. Podemos hacer esto ya que hemos programado que el botón se deshabilite en
ese caso, lo que impedirá que se llame a la función onClick.

 */