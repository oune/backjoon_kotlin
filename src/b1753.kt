import java.util.*
private val out = System.out.bufferedWriter()
fun main() = with(System.`in`.bufferedReader()) {
    val (v, e) = readLine().split(" ").map{ it.toInt() }
    val start = readLine().toInt()
    val graph = Array(v + 1) { ArrayList<Pair<Int, Int>>() }
    val ans = Array(v + 1) { Int.MAX_VALUE }
    val que = PriorityQueue{ a:Pair<Int, Int>, b:Pair<Int, Int> -> when {
        a.second < b.second -> -1
        a.second > b.second -> 1
        else -> 0
        }
    }
    repeat(e) {
        val (a, b, cost) = readLine().split(" ").map{ it.toInt() }
        graph[a].add(Pair(b, cost))
    }

    ans[start] = 0
    que.offer(Pair(start, 0))

    while(que.isNotEmpty()) {
        val (pos, cost) = que.poll()

        if (ans[pos] < cost)
            continue

        for ((w, wCost) in graph[pos]) {
            if (ans[w] > ans[pos] + wCost) {
                ans[w] = ans[pos] + wCost
                que.offer(Pair(w, ans[w]))
            }
        }
    }

    for (i in 1 until ans.size) {
        if (ans[i] == Int.MAX_VALUE)
            out.appendLine("INF")
        else
            out.appendLine(ans[i].toString())
    }
    out.flush()
}