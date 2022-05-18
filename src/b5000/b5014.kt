package test.b5000

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (f, s, g, u, d) = readLine().split(" ").map { it.toInt() }

    val visited = BooleanArray(f + 1) { false }
    val que = LinkedList<Int>()
}