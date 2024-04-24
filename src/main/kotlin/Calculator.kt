class Calculator {
    fun plus(num1: Double, num2: Double): Double {
        return Plus().operate(num1, num2)
    }

    fun minus(num1: Double, num2: Double): Double {
        return Minus().operate(num1, num2)
    }

    fun multiply(num1: Double, num2: Double): Double {
        return Multiply().operate(num1, num2)
    }

    fun divide(num1: Double, num2: Double): Double {
        return Divide().operate(num1, num2)
    }

    fun mod(num1: Double, num2: Double): Double {
        return Mod().operate(num1, num2)
    }
}