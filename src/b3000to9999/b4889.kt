package test.b3000to9999

import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var line = 1
    while(true) {
        val input = readLine()
        val stack = mutableListOf<Int>()
        var cnt = 0

        if (input[0] == '-')
            break

        input.forEach {
            if (it == '{') {
                stack.add(0)
            }
            if (it == '}') {
                if (stack.isEmpty()) {
                    stack.add(0)
                    cnt++
                } else {
                    stack.removeLast()
                }
            }
        }
        cnt += stack.size / 2

        bw.appendLine("$line. $cnt")

        line++;
    }

    bw.flush()
    bw.close()
}