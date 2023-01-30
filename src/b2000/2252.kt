package b2000import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val graph = Array(n + 1) {
        LinkedList<Int>()
    }

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].offer(b)
    }

    val inDegree = IntArray(n + 1) { 0 }
    graph.forEach { list ->
        list.forEach {
            inDegree[it]++
        }
    }

    val que = PriorityQueue<Int>()
    inDegree.forEachIndexed { index, i ->
        if (index != 0)
            if (i == 0)
                que.offer(index)
    }

    val res = LinkedList<Int>()
    while(que.isNotEmpty()) {
        val now = que.poll()
        res.offer(now)

        graph[now].forEach {
            inDegree[it]--
            if (inDegree[it] == 0)
                que.offer(it)
        }
    }

    println(res.joinToString(" "))
}