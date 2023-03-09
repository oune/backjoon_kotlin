import java.util.PriorityQueue

/*
최소신장트리
입력받은 비용들로 최소의 비용으로 트리를 구성한뒤
남은 비용을 기부함.
쿠르스칼 알고리즘을 이용하여 최소의 비용을 구하고, 모든정점들이 연결되지 않았는지 확인한후
연결 되었다면 전체 비용을 이용하여 기부할 비용을 계산
아니면 불가능함을 출력함
 */
fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()

    val input = List(size) {
        val line = readLine().map {
            when (it) {
                '0' -> 0
                in 'a'..'z' -> {
                    it - 'a'+ 1
                }
                else -> {
                    it - 'A' + 27
                }
            }
        }
        line
    }
    data class Edge(val from:Int, val to:Int, val cost:Int)
    val que = PriorityQueue<Edge>(compareBy { it.cost })

    for (i in input.indices) {
        for (j in input[i].indices) {
            val cost = input[i][j]
            if (cost != 0)
                que.offer(Edge(i, j, input[i][j]))
        }
    }

    val parents = IntArray(size) { it }
    fun findSet(idx:Int): Int {
        if (parents[idx] != idx)
            parents[idx] = findSet(parents[idx])

        return parents[idx]
    }

    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)
        parents[pa] = pb
    }

    fun isUnion(a:Int, b:Int):Boolean {
        return findSet(a) == findSet(b)
    }

    var sum = 0
    while(que.isNotEmpty()) {
        val (from ,to, cost) = que.poll()

        if (isUnion(from, to))
            continue

        unionSet(from, to)
        sum += cost
    }

    val isAllConnected = parents.all {
        findSet(it) == findSet(parents.first())
    }

    if (isAllConnected) {
        val total = input.sumOf { it.sum() }
        println(total - sum)
    } else {
        println(-1)
    }
}
