import java.util.LinkedList

/*
다익스트라
양방향 도로 한번에 한집에만 떡을 돌림
하루에 갈수 있는 거리의 제한이 존재
다 돌리는데 얼마나 걸리나?
양방향 그래프이기 때문에 가는 비용과 오는 비용이 동일함.
집의 번호는 0번 부터 시작
다익스트라를 이용하여 집으로 부터 다른 집까지의 최단경로를 계산
구한 최단경로를 정렬
최단 경로의 왕복거리로 거리를 합치면서 제한 거리에 속하는지 확인
제한 거리를 넘으면 누적 거리를 초기화 하고 다시 저장, 날자를 늘림

최대 비용: 100,000 (간선의 개수) * 10,000(간선의 최대 비용) * 2 (왕복)

94% 틀렸습니다.
모두 방문할 수 없는 조건 확인 안함.
모두 방문할 수 없는 조건은 왕복거리가 제한보다 큰 경우 모두 방문 할 수 없음.

30분 소요
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, limit, start) = readLine().split(" ").map { it.toInt() }
    val map = List(n) {
        LinkedList<Pair<Int, Int>>()
    }
    repeat(m) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].add(Pair(to, cost))
        map[to].add(Pair(from, cost))
    }

    val ans = IntArray(n) { Int.MAX_VALUE }
    val que = LinkedList<Pair<Int, Int>>()

    ans[start] = 0
    que.add(Pair(start, 0))

    while(que.isNotEmpty()) {
        val (now, cost) = que.poll()

        if (ans[now] < cost)
            continue

        for((next, nextCost) in map[now]) {
            val newCost = ans[now] + nextCost

            if (ans[next] > newCost) {
                ans[next] = newCost
                que.offer(Pair(next, newCost))
            }
        }
    }
    val distances = ans.sorted().drop(1).map { it * 2 }
    val impossible = distances.any { it > limit }

    if (impossible) {
        println(-1)
    } else {
        val distances = ans.sorted().drop(1).map { it * 2 }

        var sum = 0
        var day = 0
        for (distance in distances) {
            if (sum + distance > limit) {
                sum = distance
                day++
            } else {
                sum += distance
            }
        }
        if (sum != 0) {
            day++
        }

        println(day)
    }
}