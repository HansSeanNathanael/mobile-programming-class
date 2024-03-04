package com.example.simplecalculator.functionality

class Operand(var value : ArrayDeque<Char> = ArrayDeque()) : Formula {

    fun addDigit(value : Char) {
        if (value < '0' || value > '9' || (value == '0' && this.value.size == 1 && this.value.first() == '0')) {
            return
        }

        this.value.add(value)
    }

    fun removeDigit() {
        this.value.removeLast()
    }

    fun addPoint() {
        if (this.value.isEmpty()) {
            this.addDigit('0')
            this.value.add('.')
            return
        }

        if (!this.value.contains('.')) {
            this.value.add('.')
        }
    }

    fun percentage() {
        val num = this.getValue() / 100.0
        this.value.clear()

        if (num % 1.0 == 0.0) {
            this.value.addAll("%.0f".format(num).toList())
        } else {
            this.value.addAll(num.toString().toList())
        }
    }

    fun getString() : String {
        if (this.value.isEmpty()) {
            return "0"
        }

        val result: StringBuilder = StringBuilder()
        for (c in this.value) {
            result.append(c)
        }

        return result.toString()
    }

    fun getValue() : Double {
        return this.getString().toDouble()
    }

    fun isEmpty() : Boolean {
        return this.value.isEmpty()
    }
}