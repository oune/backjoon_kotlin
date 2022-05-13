package test.b2000

import kotlin.math.ceil

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    var left = 1
    var right = 0
    val jewelry = Array(m) {
        val num = readLine().toInt()
        if (num > right)
            right = num
        num
    }

    var res = 0
    while(left <= right) {
        val mid = (left + right) / 2
        val cnt = jewelry.fold(0) { acc, cnt ->
            acc + ceil(cnt / mid.toDouble()).toInt()
        }
        if (cnt > n) {
            left = mid + 1
        } else {
            right = mid - 1
            res = mid
        }
    }
    print(res)
}