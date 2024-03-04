package com.example.simplecalculator

import com.example.simplecalculator.functionality.Calculator
import com.example.simplecalculator.functionality.Operator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorUnitTest {

    @Test
    fun typing_isCorrect() {
        val calculator : Calculator = Calculator()

        calculator.addNumber('1')
        calculator.addNumber('2')
        calculator.addPoint()
        calculator.addNumber('3')
        calculator.addNumber('4')

        assertEquals("12.34", calculator.getString())
        assertEquals("12.34", calculator.getValue())

        calculator.addPoint()
        calculator.addNumber('5')
        assertEquals("12.345", calculator.getString())
        assertEquals("12.345", calculator.getValue())

        calculator.backspace()
        assertEquals("12.34", calculator.getString())
        assertEquals("12.34", calculator.getValue())

        calculator.backspace()
        assertEquals("12.3", calculator.getString())
        assertEquals("12.3", calculator.getValue())

        calculator.addPoint()
        calculator.addNumber('5')
        assertEquals("12.35", calculator.getString())
        assertEquals("12.35", calculator.getValue())

        calculator.backspace()
        calculator.backspace()
        assertEquals("12.", calculator.getString())
        assertEquals("12", calculator.getValue())

        calculator.backspace()
        assertEquals("12", calculator.getString())
        assertEquals("12", calculator.getValue())


        calculator.backspace()
        calculator.addPoint()
        calculator.addPoint()
        calculator.addNumber('7')
        assertEquals("1.7", calculator.getString())
        assertEquals("1.7", calculator.getValue())

        calculator.backspace()
        calculator.backspace()
        assertEquals("1", calculator.getString())
        assertEquals("1", calculator.getValue())

        calculator.backspace()
        assertEquals("0", calculator.getString())
        assertEquals("0", calculator.getValue())


        calculator.backspace()
        calculator.backspace()
        calculator.addNumber('0')
        assertEquals("0", calculator.getString())
        assertEquals("0", calculator.getValue())

        calculator.addPoint()
        assertEquals("0.", calculator.getString())
        assertEquals("0", calculator.getValue())

        calculator.addNumber('0')
        calculator.addNumber('6')
        assertEquals("0.06", calculator.getString())
        assertEquals("0.06", calculator.getValue())

        calculator.backspace()
        calculator.backspace()
        assertEquals("0.", calculator.getString())
        assertEquals("0", calculator.getValue())

        calculator.backspace()
        calculator.backspace()
        calculator.addPoint()
        assertEquals("0.", calculator.getString())
        assertEquals("0", calculator.getValue())

        calculator.addNumber('5')
        calculator.addPoint()
        assertEquals("0.5", calculator.getString())
        assertEquals("0.5", calculator.getValue())
    }

    @Test
    fun percentage_isCorrect() {
        val calculator : Calculator = Calculator()

        calculator.addNumber('5')
        calculator.addPoint()
        calculator.percentage()
        assertEquals("0.05", calculator.getString())
        assertEquals("0.05", calculator.getValue())

        calculator.addNumber('7')
        calculator.addNumber('7')
        calculator.percentage()
        assertEquals("5.77E-4", calculator.getString())
        assertEquals("5.77E-4", calculator.getValue())

        calculator.percentage()
        calculator.percentage()
        assertEquals("5.770000000000001E-8", calculator.getString())
        assertEquals("5.770000000000001E-8", calculator.getValue())


        calculator.addNumber('9')
        calculator.addNumber('9')
        assertEquals("5.770000000000001E-899", calculator.getString())
        assertEquals("0", calculator.getValue())


        calculator.backspace()
        calculator.backspace()
        calculator.backspace()
        assertEquals("5.770000000000001E-", calculator.getString())
        assertEquals("ERROR!", calculator.getValue())


        calculator.backspace()
        calculator.backspace()
        calculator.backspace()
        calculator.backspace()
        assertEquals("5.7700000000000", calculator.getString())
        assertEquals("5.77", calculator.getValue())
    }


    @Test
    fun operator_isCorrect() {
        val calculator : Calculator = Calculator()

        calculator.addNumber('8')
        calculator.addOperator(Operator.SUBTRACTION)
        calculator.addNumber('3')
        calculator.addOperator(Operator.MULTIPLICATION)
        calculator.addNumber('5')
        assertEquals("8 - 3 x 5", calculator.getString())
        assertEquals("-7", calculator.getValue())


        calculator.addOperator(Operator.ADDITION)
        calculator.addNumber('1')
        calculator.addNumber('2')
        assertEquals("8 - 3 x 5 + 12", calculator.getString())
        assertEquals("5", calculator.getValue())


        calculator.addOperator(Operator.DIVISION)
        calculator.addNumber('4')
        assertEquals("8 - 3 x 5 + 12 / 4", calculator.getString())
        assertEquals("-4", calculator.getValue())


        calculator.backspace()
        assertEquals("8 - 3 x 5 + 12 / ", calculator.getString())
        assertEquals("5", calculator.getValue())

        calculator.clear()
        calculator.addNumber('1')
        calculator.addOperator(Operator.ADDITION)
        calculator.addNumber('2')
        calculator.addOperator(Operator.DIVISION)
        calculator.addNumber('3')
        assertEquals("1 + 2 / 3", calculator.getString())
        assertEquals("1.6666666666666665", calculator.getValue())

        calculator.clear()
        calculator.addNumber('1')
        calculator.addOperator(Operator.ADDITION)
        calculator.addNumber('3')
        calculator.addOperator(Operator.MULTIPLICATION)
        calculator.addNumber('5')
        calculator.addPoint()
        calculator.addOperator(Operator.ADDITION)
        calculator.addNumber('1')
        assertEquals("1 + 3 x 5. + 1", calculator.getString())
        assertEquals("17", calculator.getValue())


        calculator.clear()
        calculator.addNumber('2')
        calculator.addNumber('5')
        calculator.addPoint()
        calculator.addNumber('6')
        calculator.addNumber('8')
        calculator.addNumber('2')
        calculator.addOperator(Operator.MULTIPLICATION)
        calculator.addNumber('3')
        calculator.addNumber('6')
        calculator.addPoint()
        calculator.addNumber('5')
        calculator.addNumber('5')
        calculator.addNumber('9')
        calculator.addOperator(Operator.DIVISION)
        calculator.addNumber('3')
        calculator.addPoint()
        calculator.addNumber('3')
        calculator.addNumber('6')
        calculator.addNumber('5')
        calculator.addOperator(Operator.SUBTRACTION)
        calculator.addNumber('2')
        calculator.addNumber('5')
        calculator.addNumber('3')
        calculator.addPoint()
        calculator.addNumber('3')
        calculator.addOperator(Operator.ADDITION)
        calculator.addPoint()
        calculator.addNumber('2')
        calculator.addOperator(Operator.DIVISION)
        calculator.addNumber('2')
        calculator.addPoint()
        calculator.addNumber('5')
        calculator.addOperator(Operator.MULTIPLICATION)
        calculator.addNumber('6')
        calculator.addPoint()
        calculator.addNumber('1')
        assertEquals("25.682 x 36.559 / 3.365 - 253.3 + 0.2 / 2.5 x 6.1", calculator.getString())
        assertEquals("26.209764635958322", calculator.getValue())
    }
}