package test.b1000

import java.util.*

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val que = LinkedList<Int>()
    repeat(n) {
        que.offer(it + 1)
    }

    val out = System.out.bufferedWriter()
    out.append("<")

    var count = 1
    while(que.size != 1) {
        if (count == k) {
            val res = que.poll()
            count = 1
            out.append("$res, ")
        } else {
            que.offer(que.pop())
            count++
        }
    }
    val last = que.poll()
    out.append("$last>")
    out.flush()
}