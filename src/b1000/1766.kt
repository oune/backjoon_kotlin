import java.util.LinkedList
import java.util.PriorityQueue

/*
위상정렬
기본적으로 먼저 풀기 좋은 문제를 먼저 선택 할 수 있도록 위상정렬을 사용
그뒤 que를 이용하여 bfs 를 진행할때 우선순위큐를 이용하여 비용이 낮은, 즉 쉬운문제 부터 풀도록 함.
문제의 번호는 1부터 시작함.
6분
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (problemCnt, edgeCnt) = readLine().split(" ").map { it.toInt() }
    val map = List(problemCnt + 1) {
        LinkedList<Int>()
    }

    val indegree = IntArray(map.size) { 0 }
    repeat(edgeCnt) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        map[from].add(to)
        indegree[to]++
    }

    val que = PriorityQueue<Int>()
    for (i in 1 .. indegree.lastIndex) {
        if (indegree[i] == 0)
            que.offer(i)
    }

    val ans = mutableListOf<Int>()
    while(que.isNotEmpty()) {
        val now = que.poll()

        ans.add(now)

        for (next in map[now]) {
            indegree[next]--
            if (indegree[next] == 0)
                que.offer(next)
        }
    }

    println(ans.joinToString(" "))

}