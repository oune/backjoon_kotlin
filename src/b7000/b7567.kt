package b9000

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()
    var pre = '/'
    var res = 0
    input.forEach {
        res += if (pre == it)
            5
        else
            10
        pre = it
    }

    print(res)
}