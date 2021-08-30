package b10000

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val stack = Stack<Int>()
    val intCount = readLine().toInt()
    repeat(intCount) {
        val input = readLine().toInt()
        if (input != 0) {
            stack.push(input)
        } else {
            stack.pop()
        }
    }

    var sum = 0
    stack.forEach {
        sum += it
    }

    print(sum)
}