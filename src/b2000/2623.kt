import java.util.LinkedList

/*
위상정렬
정석적인 위상정렬
시간 복잡도는 O(n + e)
입력을 받을때 개수와 순서가 입력됨
사이클 여부를 확인하는것이 필요.
 */
fun main() = with(System.`in`.bufferedReader()) {
    val (singerCnt, lineCnt) = readLine().split(" ").map { it.toInt() }
    val map = List(singerCnt + 1) {
        LinkedList<Int>()
    }

    val indegree = IntArray(map.size) { 0 }
    repeat(lineCnt) {
        val line = readLine().split(" ").map { it.toInt() }.drop(1)
        var from = line.first()
        for (to in line.drop(1)) {
            indegree[to]++
            map[from].add(to)

            from = to
        }
    }

    val que = LinkedList<Int>()
    for (i in 1 .. indegree.lastIndex) {
        if (indegree[i] == 0) {
            que.offer(i)
        }
    }

    val ans = mutableListOf<Int>()
    while (que.isNotEmpty()) {
        val now = que.poll()

        ans.add(now)

        for (next in map[now]) {
            indegree[next]--
            if (indegree[next] == 0)
                que.offer(next)
        }
    }
    val isCycle = ans.size != singerCnt
    println(if (isCycle) 0 else ans.joinToString("\n"))
}