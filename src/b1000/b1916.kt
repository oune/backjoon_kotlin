package b1000

import java.util.*
import kotlin.collections.ArrayList

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val graph = Array(n + 1) { ArrayList<Pair<Int, Int>>() }
    repeat(m) {
        val (a, b, cost) = readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, cost))
    }
    val (start, end) = readLine().split(" ").map{ it.toInt()}

    val que = PriorityQueue { a:Pair<Int,Int>, b:Pair<Int,Int> -> when {
        a.second < b.second -> -1
        a.second > b.second -> 1
        else -> 0
    }
    }
    val ans = Array(n + 1) { Int.MAX_VALUE }

    ans[start] = 0
    que.offer(Pair(start, 0))

    while(que.isNotEmpty()) {
        val (pos, cost) = que.poll()

        if (ans[pos] < cost)
            continue

        for ( (w, wCost) in graph[pos]) {
            if (ans[w] > ans[pos] + wCost) {
                ans[w] = ans[pos] + wCost
                que.offer(Pair(w, ans[w]))
            }
        }
    }

    print(ans[end])
}