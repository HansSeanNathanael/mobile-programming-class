package com.example.simplecalculator.partial

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.simplecalculator.functionality.Calculator
import com.example.simplecalculator.functionality.Operator
import java.util.Stack

enum class CalculatorButtonEvent {
    ALL_CLEAR, CHANGE_FORMULA, RESULT
}

class CalculatorButtonData(
    var text: String,
    var textColor: Color,
    var backgroundColor: Color,
    var listener: () -> Unit
)

class CalculatorButton(val listener: (event: CalculatorButtonEvent) -> Unit) {

    var listCalculatorButton: MutableList<CalculatorButtonData> = mutableListOf()
    val calculator: Calculator = Calculator()

    init {
        listCalculatorButton.addAll(
            listOf(
                CalculatorButtonData(
                    "AC", Color.Green, Color.White
                ) {
                    if (this.calculator.isEmpty()) {
                        this.listener.invoke(CalculatorButtonEvent.ALL_CLEAR)
                    } else {
                        this.calculator.clear()
                        this.listCalculatorButton[0].text = "AC"
                        this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                    }
                },

                CalculatorButtonData(
                    "DEL", Color.Green, Color.White
                ) {
                    this.calculator.backspace()
                    if (this.calculator.isEmpty()) {
                        this.listCalculatorButton[0].text = "AC"
                    }
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },

                CalculatorButtonData(
                    "%", Color.Green, Color.White
                ) {
                    this.calculator.percentage()
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData(
                    "/", Color.Green, Color.White
                ) {
                    this.calculator.addOperator(Operator.DIVISION)
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData(
                    "7", Color.Green, Color.White
                ) {
                    this.calculator.addNumber('7')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData(
                    "8", Color.Green, Color.White
                ) {
                    this.calculator.addNumber('8')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData(
                    "9", Color.Green, Color.White
                ) {
                    this.calculator.addNumber('9')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("x", Color.Green, Color.White) {
                    this.calculator.addOperator(Operator.MULTIPLICATION)
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                                                                    },
                CalculatorButtonData("4", Color.Green, Color.White) {
                    this.calculator.addNumber('4')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("5", Color.Green, Color.White) {
                    this.calculator.addNumber('5')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("6", Color.Green, Color.White) {
                    this.calculator.addNumber('6')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("-", Color.Green, Color.White) {
                    this.calculator.addOperator(Operator.DIVISION)
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("1", Color.Green, Color.White) {
                    this.calculator.addNumber('1')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("2", Color.Green, Color.White) {
                    this.calculator.addNumber('2')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("3", Color.Green, Color.White) {
                    this.calculator.addNumber('3')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("+", Color.Green, Color.White) {
                    this.calculator.addOperator(Operator.ADDITION)
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("", Color.Green, Color.White) {},
                CalculatorButtonData("0", Color.Green, Color.White) {
                    this.calculator.addNumber('0')
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData(".", Color.Green, Color.White) {
                    this.calculator.addPoint()
                    this.listener.invoke(CalculatorButtonEvent.CHANGE_FORMULA)
                },
                CalculatorButtonData("=", Color.Green, Color.White) {
                    this.listener.invoke(CalculatorButtonEvent.RESULT)
                }
            )
        )
    }

    fun getFormula() : String {
        return this.calculator.getString()
    }

    fun getResult() : String {
        return this.calculator.getValue()
    }

    @Composable
    fun render() {

        LazyVerticalGrid(columns = GridCells.Fixed(4)) {
            items(listCalculatorButton) { item ->
                Button(onClick = item.listener) {
                    Text(text = item.text)
                }
            }
        }
    }
}