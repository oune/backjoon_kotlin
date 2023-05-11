import java.util.PriorityQueue
/*
* 그리디
* */
fun main() {
    val cnt = readln().toInt()

    data class Class(val id:Int, val start:Int, val end:Int)
    val list = List(cnt) {
        val (id, start, end) =  readln().split(" ").map { it.toInt() }
        Class(id, start, end)
    }

    val que = PriorityQueue<Int>()
    que.add(0)

    val sorted = list.sortedBy { it.start }
    for ((id, start, end) in sorted) {
        val min = que.poll()

        if (start < min)
            que.add(min)
        que.add(end)
    }

    println(que.size)
}