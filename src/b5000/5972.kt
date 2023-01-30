package b5000
import java.util.ArrayList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val graph = Array(n + 1) {
        ArrayList<Pair<Int, Int>>()
    }

    repeat(m) {
        val (from, to, cow) = readLine().split(" ").map { it.toInt() }
        graph[from].add(Pair(to, cow))
        graph[to].add(Pair(from, cow))
    }

    val que = PriorityQueue( compareBy<Pair<Int, Int>> { it.second } )
    val ans = IntArray(n + 1) { Int.MAX_VALUE}

    ans[1] = 0
    que.offer(Pair(1, 0))
    while(que.isNotEmpty()) {
        val (now, cow) = que.poll()

        if (cow > ans[now])
            continue


        for ( (to, toCow) in graph[now]) {
            if (ans[to] > ans[now] + toCow) {
                ans[to] = ans[now] + toCow
                que.offer(Pair(to, ans[to]))
            }
        }
    }

    println(ans.last())
}