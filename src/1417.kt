import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val counts = List(n) {
        readln().toInt()
    }

    var me = counts.first()
    var res = 0

    val que = PriorityQueue<Int>(compareByDescending { it })
    counts.drop(1).forEach { count->
        que.add(count)
    }

    while (que.isNotEmpty()) {
        val now = que.poll()

        if (now >= me) {
            que.add(now - 1)
            me++
            res++
        }
    }

    println(res)
}