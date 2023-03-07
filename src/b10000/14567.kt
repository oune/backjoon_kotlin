import java.util.LinkedList

/*
위상정렬
O(v + e) 정점과 간선의 합 만큼 시간복잡도
일반적인 위상정렬을 하지만 bfs 탐색 결과로 해당 노드의 깊이를 저장하고 그 결과물을 출력함.
10분
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (n, e) = readLine().split(" ").map { it.toInt() }
    val map = List(n + 1) {
        mutableListOf<Int>()
    }

    val indegree = IntArray(map.size) { 0 }
    repeat(e) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        map[from].add(to)
        indegree[to]++
    }

    val que = LinkedList<Pair<Int, Int>>()
    for (i in 1..indegree.lastIndex) {
        if (indegree[i] == 0)
            que.offer(Pair(i , 1))
    }

    val ans = IntArray(map.size) { 0 }
    while (que.isNotEmpty()) {
        val (now, depth)= que.poll()

        ans[now] = depth

        for (next in map[now]) {
            indegree[next]--

            if (indegree[next] == 0)
                que.offer(Pair(next, depth + 1))
        }
    }

    println(ans.toList().drop(1).joinToString(" "))
}