import java.util.LinkedList

/*
위상정렬..?
왜 이게 위상정렬이라는 거지 그냥 dp 그래프 탐색 아닌가? 알고리즘 분류라는 건 무엇으로 가는가.
아니면 위상정렬의 정의는 무엇인가. 너무 복잡하다 복잡해
가중치의 값이 10,000, 도로의 개수는 100,000
최대 가중치는 1,000,000,000 >> 10억 Int 범위 내
틀린 이유) 지나간 경로의 길이가 아니라
경로에 포함된 도로의 개수, 따라서 중복된 도로는 개수를 세지 않음.
 */
fun main() = with(System.`in`.bufferedReader()) {
    val cityCnt = readLine().toInt()
    val roadCnt = readLine().toInt()

    val map = List(cityCnt + 1) {
        LinkedList<Pair<Int, Int>>()
    }
    val reversedMap = List(cityCnt + 1) {
        LinkedList<Pair<Int, Int>>()
    }

    val indegree = IntArray(map.size) { 0 }
    repeat(roadCnt) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        map[from].add(Pair(to, cost))
        reversedMap[to].add(Pair(from, cost))
        indegree[to]++
    }

    val (startCity, endCity) = readLine().split(" ").map { it.toInt() }
    val maxArr = IntArray(indegree.size) { 0 }

    fun tss() {
        val que = LinkedList<Int>()
        que.offer(startCity)


        while(que.isNotEmpty()) {
            val now = que.poll()

            for (next in map[now]) {
                indegree[next.first]--

                if (indegree[next.first] == 0)
                    que.offer(next.first)

                maxArr[next.first] = maxArr[next.first].coerceAtLeast(maxArr[now] + next.second)
            }
        }
    }
    tss()

    fun bfs() {

    }

    println(maxArr[endCity])
}