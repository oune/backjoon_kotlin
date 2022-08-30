package test.b1000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (_, repeat, load) = readLine().split(' ').map { it.toInt() }
    val que = PriorityQueue<Int>(reverseOrder())
    var pre = 0
    readLine().split(' ').map { it.toInt() }.sorted().forEach {
        que.offer(it - pre)
        pre = it
    }
    que.offer(load - pre)

    repeat(repeat) {
        val now = que.poll()
        val half = now / 2
        if (now % 2 == 0) {
            repeat(2) {
                que.offer(half)
            }
        } else {
            que.offer(half)
            que.offer(half + 1)
        }
    }

    println(que.poll())
}