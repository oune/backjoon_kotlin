package test.b1000

import kotlin.math.ceil
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val start = ceil(sqrt(readLine().toInt().toDouble())).toInt()
    val end = sqrt(readLine().toInt().toDouble()).toInt()

    if (start > end) {
        println("-1")
        return
    }

    var sum = 0
    for (i in start .. end) {
        sum += i * i
    }

    println(sum)
    println(start * start)
}