import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val list = List(n) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        Pair(start, end)
    }

    val que = PriorityQueue<Int>()
    que.add(0)
    for ((start, end) in list.sortedBy { it.first }) {
        val last = que.poll()

        if (start < last)
            que.add(last)
        que.add(end)
    }

    println(que.size)
}