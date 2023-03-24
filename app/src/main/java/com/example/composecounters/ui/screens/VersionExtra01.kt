package com.example.composecounters.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun VersionExtra01() {
    val contador1 = rememberSaveable { mutableStateOf(0) }
    val contador2 = rememberSaveable { mutableStateOf(0) }
    val contador3 = rememberSaveable { mutableStateOf(0) }
    val contadorGlobal = rememberSaveable { mutableStateOf(0) }
    val incrementos = (1..5).toList()
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Contador("Contador 1", contador1, contadorGlobal, incrementos)
        Contador("Contador 2", contador2, contadorGlobal, incrementos)
        Contador("Contador 3", contador3, contadorGlobal, incrementos)

        Spacer(Modifier.height(32.dp))

        ContadorGlobal(contador1, contador2, contador3, contadorGlobal)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Contador(titulo: String, contador: MutableState<Int>, contadorGlobal: MutableState<Int>, incrementos: Iterable<Int>) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }


    Column(
        Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(titulo)

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    contador.value += selectedOptionText.toInt()
                    contadorGlobal.value += selectedOptionText.toInt()},
                Modifier.padding(end = 20.dp)
            ) {
                Text("Incrementar")
            }

            TextField(
                value = contador.value.toString(),
                onValueChange = { },
                modifier = Modifier.width(64.dp),
                enabled = false
            )

            IconButton(
                onClick = { contador.value = 0 }
            ) {
                Icon(Icons.Filled.Refresh, null)
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Incremento: ")
            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = {
                    expanded = !expanded
                },
                modifier = Modifier
                    .fillMaxWidth(0.3f)
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { },
                    //label = { Text("hola") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    incrementos.forEach { incremento ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = incremento.toString()
                                expanded = false
                            }
                        ) {
                            Text(incremento.toString())
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun ContadorGlobal(
    contador1: MutableState<Int>,
    contador2: MutableState<Int>,
    contador3: MutableState<Int>,
    contadorGlobal: MutableState<Int>
) {
    Column(
        Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Text("Contador global")
        }
        Row() {
            Spacer(Modifier.height(16.dp))

            TextField(
                value = contadorGlobal.value.toString(),
                onValueChange = { },
                modifier = Modifier.width(64.dp),
                enabled = false
            )

            IconButton(
                onClick = { contadorGlobal.value = 0 }
            ) {
                Icon(Icons.Filled.Refresh, null)
            }
        }
    }
}



