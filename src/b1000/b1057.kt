package b1000

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, kim, lim) = readLine().split(" ").map{ it.toInt() }
    val a = kim - 1
    val b = lim - 1

    var res = -1
    for (it in 1..n) {
        val num = 2.0.pow(it).toInt()

        if (a / num == b / num) {
            res = it
            break
        }
    }

    print(res)
}