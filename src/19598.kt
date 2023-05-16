import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    data class Meeting(val start:Int, val end:Int)

    val meetings = List(n) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        Meeting(start, end)
    }

    val que = PriorityQueue<Int>()
    que.add(0)

    val sorted = meetings.sortedBy { it.start }
    for ((start, end) in sorted) {
        val min = que.poll()

        if (start < min)
            que.add(min)
        que.add(end)
    }

    println(que.size)
}