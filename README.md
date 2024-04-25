Kotlin 프로그래밍 기초 주차
===============================
나만의 계산기 만들기 과제
------------------------------ 
### 환경 설정



SDK 버전 : Eclipse Temurin 18.0.2.1

### 과제 필수 요구 사항


LV 1.
사칙 연산을 수행할 Calculator 클래스 만들기

    이를 구현한 방법    

    - 더하기 연산 : fun plus() / 빼기 연산 : fun minus() / 곱하기 연산 : fun multiply() / 나누기 연산 : fun divide()

    - Calculator.kt 파일에 Calculator class를 생성하여 위 연산 함수들을 집어넣음



LV 2.
LV 1의 연산에 "나머지 연산" 추가하기

    - 함수 이름 : fun mod()로 생성
    - 과정은 LV 1.과 동일
LV 3.
연산 함수들을 각각의 클래스로 만들고 Calculator 클래스와 관계 맺기

    이를 구현한 방법
    
    - 더하기, 빼기, 곱하기, 나누기, 나머지 구하기 class 파일 만들기

    - Cauculator class에서 매개변수를 입력하면 각 연산 함수를 호출하는 방식
        
        # 각 연산 함수의 이름을 fun operate()로 통일
        # Calculator class에서 입력된 연산자와 맞는 함수를 호출하여 각 연산의 결과값을 반환함



### 내가 추가한 기능

1. Y 키를 계산기를 종료하는 키로 지정하고, 입력 시 종료되도록



```kotlin
fun main() {
...중략...
        println("계산기 종료 여부 (Y 키를 입력하면 계산기를 종료합니다)")
        val inputKey = readLine()?.lowercase()
        if (inputKey == "y") {
            println("종료합니다")
            break
        }
        continue
    }
}
```

- lowercase() 함수 적용 -> 대문자를 입력하면 소문자로 변환
- Y 키, y 키 어느 키를 입력하여도 계산기를 종료할 수 있다.
- 다른 키를 입력하면 continue에 의해 while 로 되돌아감 (num1 입력 단계)

(1) Y 키를 입력하지 않았을 때

![계산기 종료 x](https://github.com/jsun1109/CalculatorTask/assets/161568081/6ca3369c-5717-4996-b1e0-991a0e312846)

(2) Y 키를 입력하였을 때

![계산기 종료 - Y](https://github.com/jsun1109/CalculatorTask/assets/161568081/d8162e86-d1ce-4aa8-84a9-3ce6cbd42028)

(3) y 키를 입력하였을 때

![계산기 종료 - y](https://github.com/jsun1109/CalculatorTask/assets/161568081/c70b9ece-87bb-4153-bba3-fad30de611d0)

2. 입력되는 숫자를 Double형으로 변환


    - 현재 만든 계산기 코드로는 자료형을 강제로 지정해주어야 하기에 적용


3. 잘못된 값을 입력하면 해당 단계의 처음으로 돌아가도록


    - 숫자를 입력하는 단계 : Int/Double 타입 숫자를 입력하지 않으면 되돌아감

    - 연산자를 입력하는 단계 : 유효 연산자( + , - , * , / , % )를 제외한 문자가 입력되면 되돌아감

(1) 숫자 입력 단계

```kotlin
... 중략 ...      
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
... 중략 ...

```
- 터미널에 입력한 값을 toDoubleOrNull() 함수로 Double형 또는 null 값이 되게 함 
- Double형이 아니면(숫자는 Double형으로 변환되므로 문자가 입력된 경우임) 경고문을 출력하고, 다시 되돌아감
- 0으로 나누는 경우와 나머지를 구하는 경우 경고문을 출력하고, 다시 되돌아감 


문자가 입력되었을 때(빈 칸 포함)

![숫자 입력 x](https://github.com/jsun1109/CalculatorTask/assets/161568081/6a443627-e266-46ee-b87c-c30e34f18e41)

숫자가 입력되었을 때

![숫자 입력](https://github.com/jsun1109/CalculatorTask/assets/161568081/4d6d8388-f1ef-405b-839a-48451882f47b)

0으로 나눌 때

![나누기 0](https://github.com/jsun1109/CalculatorTask/assets/161568081/e4ff479b-a965-41fe-97b1-faf2111602b3)

(2) 연산자 입력 단계

```kotlin
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
```

- 유효 연산자를 setOf 함수로 set로 만들었다.
- 입력한 문자가 set에 포함되지 않으면 되돌아감

![잘못된 연산자](https://github.com/jsun1109/CalculatorTask/assets/161568081/18a46819-d5fa-4d90-aad7-e3fe77be5025)

### 다음 구현 목표

- 숫자 타입을 강제로 지정하지 않아도 함수가 작동하게 만들기
  - 이 경우, 결과값도 Int형으로 표현 가능하게 됨
- try - catch 문을 이용한 예외처리
- 결과값을 저장하여 이전 결과값에 이어서 연산이 가능하게 만들기