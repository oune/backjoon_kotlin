import java.util.LinkedList
import kotlin.math.max

/*
위상정렬
선행관계를 가지는 것의 최소의 시간을 구하라
시간을 늘려가면서 확인하는 방식으로 구현했는데 더 나은 방식을 찾아야 겠음.

틀린 이유 (4%)
같은 깊이를 가지는 비용들중 최대값들의 합이 최소의 시간 으로 구현했을때
반례
3
1 0
1 1 1
5 2 1 2
정답 : 7

5
1 0
1 1 1
1 1 1
3 1 1
1 3 2 3 4
정답 : 5

건너 뛰어서 방문 했을 경우 동시가 아니지만, 동시로 취급이 됨
다익스트라 처럼 도착한 곳에 걸린 시간이 기존보다 더 오래걸리면 갱신하는 방식으로 구현이 가능


걸린 시간 1시간 반
반례 잘못 만들어서 더 오래 걸렸음.

 */
fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val map = List(size + 1) {
        LinkedList<Int>()
    }

    val indegree = IntArray(map.size) { 0 }
    indegree[0] = -1
    val costs = IntArray(map.size) { 0 }

    repeat(size) { i ->
        val idx = i + 1
        val input = readLine().split(" ").map { it.toInt() }
        costs[idx] = input[0]

        for (next in input.drop(2)) {
            map[next].add(idx)
            indegree[idx]++
        }
    }

    val ans = IntArray(map.size) { 0 }
    val que = LinkedList<Int>()
    for (i in indegree.indices) {
        if (indegree[i] == 0) {
            ans[i] = costs[i]
            que.offer(i)
        }
    }

    while (que.isNotEmpty()) {
        val now = que.poll()

        for (next in map[now]) {
            indegree[next]--
            ans[next] = max(ans[next], costs[next] + ans[now])

            if (indegree[next] == 0) {
                que.offer(next)
            }
        }
    }
    println(ans.max())
}