package test.b3000to9999

import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    var line = 1
    while(true) {
        val input = readLine()
        var stack = 0
        var cnt = 0

        if (input[0] == '-')
            break

        input.forEach {
            if (it == '{') {
                stack++
            }
            if (it == '}') {
                if (stack == 0) {
                    stack++
                    cnt++
                } else {
                    stack--
                }
            }
        }
        cnt += stack / 2

        bw.appendLine("$line. $cnt")

        line++;
    }

    bw.flush()
    bw.close()
}