fun main() {
    val calculator = Calculator()
    println("계산기가 실행되었습니다")
    while (true) {
        println("num1을 입력해 주세요")
        val num1 = readLine()?.toDoubleOrNull()
        if (num1 !is Double) {
            println("입력값이 숫자가 아닙니다")
            continue
        }
        var operator : String?
        while (true) {
            println("연산자를 입력해주세요 (유효 연산자 : +, - *, /, %)")
            operator = readLine()
            if (operator !in setOf("+", "-", "*", "/", "%")) {
                println("유효한 연산자가 아닙니다. 연산자 입력 단계로 돌아갑니다")
            } else {
                break
            }
        }

        while (true) {
            println("num2를 입력해 주세요")
            val num2 = readLine()?.toDoubleOrNull()
            if (num2 !is Double) {
                println("입력값이 숫자가 아닙니다")
                continue
            }
            if ((operator == "/") && num2 == 0.0) {
                println("Error: 0으로 나눌 수 없습니다")
                continue
            }
            if ((operator == "%") && num2 == 0.0) {
                println("Error: 0으로 나머지를 구할 수 없습니다")
                continue
            }

            val result = when (operator) {
                "+" -> calculator.plus(num1, num2)
                "-" -> calculator.minus(num1, num2)
                "*" -> calculator.multiply(num1, num2)
                "/" -> calculator.divide(num1, num2)
                "%" -> calculator.mod(num1, num2)
                else -> return
            }

            println("= ${result}")
            break
        }
        println("계산기 종료 여부 (Y 키를 입력하면 계산기를 종료합니다)")
        val inputKey = readLine()?.lowercase()
        if (inputKey == "y") {
            println("종료합니다")
            break
        }
        continue
    }
}