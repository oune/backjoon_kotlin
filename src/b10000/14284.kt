package b10000import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val graph = Array(n + 1) {
        LinkedList<Pair<Int, Int>>()
    }

    repeat(m) {
        val (from, to, price) = readLine().split(" ").map { it.toInt() }
        graph[from].offer(Pair(to, price))
        graph[to].offer(Pair(from, price))
    }

    val que = PriorityQueue<Pair<Int, Int>>( compareBy { it.second })
    val ans = IntArray(n + 1) {Int.MAX_VALUE}

    val (s, t) = readLine().split(" ").map { it.toInt() }
    que.offer(Pair(s, 0))
    ans[s] = 0

    while(que.isNotEmpty()) {
        val now = que.poll()

        if (now.second > ans[now.first])
            continue

        for (to in graph[now.first]) {
            if (ans[to.first] > ans[now.first] + to.second) {
                ans[to.first] = ans[now.first] + to.second
                que.offer(Pair(to.first, ans[to.first]))
            }
        }
    }

    println(ans[t])
}