package b1000

import kotlin.math.log10

fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    val count = readLine()
    val broken = if (count == "0") listOf() else readLine().split(" ").map{ it.toInt() }

    var left = Int.MIN_VALUE
    var right = Int.MAX_VALUE
    for (i in 0..10000000) {
        var isPossible = true
        var now = i

        if (now == 0)
            if (now in broken)
                isPossible = false

        while (now > 0) {
            if (now % 10 in broken) {
                isPossible = false
                break
            }
            now /= 10
        }

        if (isPossible) {
            if (i > num) {
                if (right > i)
                    right = i
            } else if (i < num) {
                if (left < i)
                    left = i
            } else {
                left = i
                right = i
                break
            }
        }
    }

    var min = if (num > 100) num - 100 else 100 - num
    listOf(left, right).forEach {
        if (it != Int.MAX_VALUE && it != Int.MIN_VALUE) {
            val lines = it.toString().length
            val count = lines + if (num > it) num - it else it - num
            if (min > count)
                min = count
        }
    }

    print(min)
}