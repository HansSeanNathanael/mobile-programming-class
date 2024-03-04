package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplecalculator.functionality.Calculator
import com.example.simplecalculator.functionality.Operator
import com.example.simplecalculator.partial.CalculatorView
import com.example.simplecalculator.partial.CalculatorViewEvent
import com.example.simplecalculator.partial.HistoryCard
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentFormula = mutableStateOf("")
        val currentResult = mutableStateOf("0")
        val historyList : SnapshotStateList<String> = mutableStateListOf()

        val calculator = Calculator()
        val calculatorView = CalculatorView()
        calculatorView.setListener {
            when(it) {
                CalculatorViewEvent.AC -> {
                    if (calculator.isEmpty()) {
                        historyList.clear()
                    }
                    else {
                        calculatorView.setACText("AC")
                    }
                    calculator.clear()
                }
                CalculatorViewEvent.DEL -> {
                    calculator.backspace()
                    if (calculator.isEmpty()) {
                        calculatorView.setACText("AC")
                    }
                }
                CalculatorViewEvent.RES -> {
                    calculatorView.setACText("AC")
                    historyList.add("${calculator.getString()} = ${calculator.getValue()}")
                    calculator.clear()
                }
                CalculatorViewEvent.POINT -> {
                    calculator.addPoint()
                }
                CalculatorViewEvent.PERCENTAGE -> {
                    calculator.percentage()
                }
                CalculatorViewEvent.DIV -> {
                    calculator.addOperator(Operator.DIVISION)
                }
                CalculatorViewEvent.MUL -> {
                    calculator.addOperator(Operator.MULTIPLICATION)
                }
                CalculatorViewEvent.ADD -> {
                    calculator.addOperator(Operator.ADDITION)
                }
                CalculatorViewEvent.SUB -> {
                    calculator.addOperator(Operator.SUBTRACTION)
                }
                CalculatorViewEvent.ZERO -> {
                    calculator.addNumber('0')
                }
                CalculatorViewEvent.ONE -> {
                    calculator.addNumber('1')
                }
                CalculatorViewEvent.TWO -> {
                    calculator.addNumber('2')
                }
                CalculatorViewEvent.THREE -> {
                    calculator.addNumber('3')
                }
                CalculatorViewEvent.FOUR -> {
                    calculator.addNumber('4')
                }
                CalculatorViewEvent.FIVE -> {
                    calculator.addNumber('5')
                }
                CalculatorViewEvent.SIX -> {
                    calculator.addNumber('6')
                }
                CalculatorViewEvent.SEVEN -> {
                    calculator.addNumber('7')
                }
                CalculatorViewEvent.EIGHT -> {
                    calculator.addNumber('8')
                }
                CalculatorViewEvent.NINE -> {
                    calculator.addNumber('9')
                }
            }
            currentFormula.value = calculator.getString()
            currentResult.value = calculator.getValue()
        }

        setContent {
            SimpleCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Render(currentFormula.value, currentResult.value, historyList, calculatorView)
                }
            }
        }
    }
}

@Composable
fun Render(currentFormula: String, currentResult: String,  history: SnapshotStateList<String>, calculatorView: CalculatorView) {

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
                .verticalScroll(
                    rememberScrollState()
                ), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
                for (st in history) {
                    HistoryCard(history = st)
                }
            }
            Text(text = currentFormula, fontSize = 32.sp)
            Text(text = "= $currentResult", fontSize = 32.sp)
        }
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black))) {

            calculatorView.Render()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCalculatorTheme {
        val temp = remember { mutableStateListOf("1", "2", "3") }
        Render("1+2+3", "6", temp, CalculatorView())
    }
}