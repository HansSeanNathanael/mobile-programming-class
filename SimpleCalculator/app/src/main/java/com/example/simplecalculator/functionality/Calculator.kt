package com.example.simplecalculator.functionality

import java.util.Stack

class Calculator {
    private val formula: ArrayDeque<Formula> = ArrayDeque()
    private val operatorStack: Stack<Operator> = Stack()
    private val operandStack: Stack<Double> = Stack()

    fun addNumber(number: Char) {
        if (!this.formula.isEmpty() && this.formula.last() is Operand) {
            (this.formula.last() as Operand).addDigit(number)
        } else {
            val newOperand = Operand()
            newOperand.addDigit(number)
            this.formula.add(newOperand)
        }
    }

    fun addPoint() {
        if (this.formula.isEmpty() || this.formula.last() is Operator) {
            this.formula.add(Operand())
            (this.formula.last() as Operand).addPoint()
        } else if (this.formula.last() is Operand) {
            (this.formula.last() as Operand).addPoint()
        }
    }

    fun percentage() {
        if (!this.formula.isEmpty() && this.formula.last() is Operand) {
            (this.formula.last() as Operand).percentage()
        }
    }

    fun addOperator(operator: Operator) {
        if (this.formula.isEmpty()) {
            return
        }

        if (this.formula.last() is Operand) {
            this.formula.add(operator)
        } else if (this.formula.last() is Operator) {
            this.formula.removeLast()
            this.formula.add(operator)
        }
    }

    fun backspace() {
        if (this.formula.isEmpty()) {
            return
        }

        if (this.formula.last() is Operator) {
            this.formula.removeLast()
        } else {
            (this.formula.last() as Operand).removeDigit()
            if ((this.formula.last() as Operand).isEmpty()) {
                this.formula.removeLast()
            }
        }
    }

    fun clear() {
        this.formula.clear()
        this.operandStack.clear()
        this.operatorStack.clear()
    }

    fun getString(): String {
        val result: StringBuilder = StringBuilder()

        for (oper in this.formula) {
            if (oper is Operand) {
                result.append(oper.getString())
            } else if (oper is Operator) {
                when (oper) {
                    Operator.ADDITION -> {
                        result.append(" + ")
                    }

                    Operator.SUBTRACTION -> {
                        result.append(" - ")
                    }

                    Operator.DIVISION -> {
                        result.append(" / ")
                    }

                    Operator.MULTIPLICATION -> {
                        result.append(" x ")
                    }
                }
            }
        }

        if (result.isEmpty()) {
            return "0"
        }

        return result.toString()
    }

    fun isEmpty(): Boolean {
        return this.formula.isEmpty()
    }

    fun getValue(): String {
        if (this.formula.isEmpty()) {
            return "0"
        }

        try {

            this.operatorStack.clear()
            this.operandStack.clear()

            for (formula in this.formula) {
                if (formula is Operand) {
                    this.operandStack.add(formula.getValue())
                }
                else if (formula is Operator) {
                    if (formula == Operator.ADDITION || formula == Operator.SUBTRACTION) {
                        while (!this.operatorStack.isEmpty()) {
                            val last = this.operandStack.pop()
                            when (this.operatorStack.pop()) {
                                Operator.MULTIPLICATION -> {
                                    this.operandStack.add(this.operandStack.pop() * last)
                                }

                                Operator.DIVISION -> {
                                    this.operandStack.add(this.operandStack.pop() / last)
                                }

                                Operator.ADDITION -> {
                                    this.operandStack.add(this.operandStack.pop() + last)
                                }

                                Operator.SUBTRACTION -> {
                                    this.operandStack.add(this.operandStack.pop() - last)
                                }

                                else -> {}
                            }
                        }
                    }
                    else if (formula == Operator.MULTIPLICATION || formula == Operator.DIVISION) {
                        while (!this.operatorStack.isEmpty() && (this.operatorStack.last() == Operator.MULTIPLICATION || this.operatorStack.last() == Operator.DIVISION)) {
                            val last = this.operandStack.pop()
                            when (this.operatorStack.pop()) {
                                Operator.MULTIPLICATION -> {
                                    this.operandStack.add(this.operandStack.pop() * last)
                                }

                                Operator.DIVISION -> {
                                    this.operandStack.add(this.operandStack.pop() / last)
                                }

                                else -> {}
                            }
                        }
                    }
                    this.operatorStack.add(formula)
                }
            }


            if (this.operandStack.size > this.operatorStack.size + 1) {
                return "ERROR!"
            }

            while (this.operatorStack.size >= this.operandStack.size) {
                this.operatorStack.pop()
            }

            while (!this.operatorStack.isEmpty()) {
                val operator = this.operatorStack.pop()
                val last = this.operandStack.pop()

                @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
                when (operator) {
                    Operator.ADDITION -> {
                        this.operandStack.add(this.operandStack.pop() + last)
                    }

                    Operator.SUBTRACTION -> {
                        this.operandStack.add(this.operandStack.pop() - last)
                    }

                    Operator.MULTIPLICATION -> {
                        this.operandStack.add(this.operandStack.pop() * last)
                    }

                    Operator.DIVISION -> {
                        this.operandStack.add(this.operandStack.pop() / last)
                    }
                }
            }

            return if (this.operandStack.last() % 1.0 == 0.0) {
                "%.0f".format(this.operandStack.last())
            } else {
                this.operandStack.last().toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "ERROR!"
        }
    }

}