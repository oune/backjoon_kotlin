package b1000

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with (BufferedReader(InputStreamReader(System.`in`))) {
    val (x, y) = readLine().split(" ").map{ it.toLong() }

    val getWinRate = fun(x: Long, y: Long): Long {
        return y * 100 / x
    }

    val z = getWinRate(x, y)

    if (z >= 99) {
        print(-1)
    } else {
        var start :Long = 1
        var end :Long = x.toLong()
        while (start <= end) {
            val mid = (start + end) / 2
            val rate = getWinRate(x + mid, y + mid)

            if (rate > z) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        print(start)
    }
}