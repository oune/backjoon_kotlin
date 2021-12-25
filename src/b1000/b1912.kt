package b1000

import java.io.BufferedReader

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val nums = readLine().split(" ").map { it.toInt() }

    var max = Int.MIN_VALUE
    var sum = 0

    nums.forEach {
        sum += it
        if (sum > max)
            max = sum

        if (sum < 0)
            sum = 0
    }

    println(max)
}