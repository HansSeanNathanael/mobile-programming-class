package com.example.simplecalculator.partial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp

enum class CalculatorViewEvent {
    AC, DEL, PERCENTAGE, DIV, MUL, ADD, SUB, RES, POINT, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE
}

class CalculatorView {

    private var acText: MutableState<String> = mutableStateOf("AC")
    private var listener: ((event: CalculatorViewEvent) -> Unit)? = null

    fun setACText(text: String) {
        this.acText.value = text
    }

    fun setListener(listener: (event: CalculatorViewEvent) -> Unit) {
        this.listener = listener
    }

    @Composable
    fun Render() {
        val operatorButtonColor = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.primary,
        )

        val operandButtonColor = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
        )

        val resultButtonColor = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
        )


        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.AC) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operatorButtonColor,
                        shape = CircleShape
                    ) {
                        val acText by this@CalculatorView.acText
                        Text(text = acText, fontSize = 12.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.DEL) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operatorButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "DEL", fontSize = 12.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.PERCENTAGE) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operatorButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "%", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.DIV) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operatorButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "/", fontSize = 24.sp)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.SEVEN) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "7", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.EIGHT) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "8", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.NINE) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "9", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.MUL) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operatorButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "x", fontSize = 24.sp)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.FOUR) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "4", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.FIVE) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "5", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.SIX) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "6", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.SUB) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operatorButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "-", fontSize = 24.sp)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.ONE) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "1", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.TWO) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "2", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.THREE) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "3", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.ADD) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operatorButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "+", fontSize = 24.sp)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.ZERO) },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "0", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.POINT) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = operandButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = ".", fontSize = 24.sp)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(4.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { this@CalculatorView.listener?.invoke(CalculatorViewEvent.RES) },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxSize()
                            .background(Color.Transparent),
                        colors = resultButtonColor,
                        shape = CircleShape
                    ) {
                        Text(text = "=", fontSize = 24.sp)
                    }
                }
            }
        }
    }
}