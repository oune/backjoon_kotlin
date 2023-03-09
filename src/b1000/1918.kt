import java.util.LinkedList

/*
구현
중위표기법을 후위표기법으로 바꾸는 문제
스택을 활용하여 변환이 필요함. 또한 연산자의 우선순위를 반영을 해야함.
 */

fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine()

    fun Char.isOperator():Boolean {
        return when(this) {
            '+', '-', '*', '/', '(', ')' -> true
            else -> false
        }
    }

    fun Char.priority():Int {
        return when(this) {
            '+', -> 0
            '-', -> 0
            '*', -> 1
            '/', -> 1
            '(' -> -1
            else -> -1
        }
    }

    val ans = LinkedList<Char>()
    val operators = LinkedList<Char>()
    for (char in input) {
        if (char.isOperator()) {
            when(char) {
                '(' -> operators.offer(char)
                ')' -> {
                    while(operators.isNotEmpty() && operators.last() != '(') {
                        ans.add(operators.removeLast())
                    }
                    operators.removeLast()
                }
                else -> {
                    while(operators.isNotEmpty() && operators.last().priority() >= char.priority()) {
                        ans.add(operators.removeLast())
                    }
                    operators.offer(char)
                }
            }

        } else {
            ans.offer(char)
        }
    }

    while(operators.isNotEmpty()) {
        ans.add(operators.removeLast())
    }

    println(ans.joinToString(""))
}
