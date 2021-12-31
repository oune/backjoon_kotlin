package b10000

import java.util.*
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val case = readLine().toInt()
    val que = PriorityQueue<Int>{ a, b ->
        val newA = if (a < 0) -a else a
        val newB = if (b < 0) -b else b

        when {
            newA < newB -> -1
            newA > newB -> 1
            a < b -> -1
            a > b -> 1
            else -> 0
        }
    }

    repeat(case) {
        val now = readLine().toInt()

        if (now != 0) {
            que.offer(now)
        } else if (que.isEmpty()) {
          println(0)
        } else {
            println(que.poll())
        }
    }
}