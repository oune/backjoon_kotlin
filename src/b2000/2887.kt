import java.util.LinkedList

/*
최소신장트리
모두 연결 되었을때의 최소의 비용을 구하라
kruskal 이용하여 최소의 비용을 구함
입력을 받을때 이미 입력받은 별들과의 비용을 계산해서 우선순위 큐에 넣음

메모리 초과
모든 경우를 다 넣는경우 n^2 개의 간선이 발생하는데 이것 때문에 메모리 초과가 발생함
따라서 x, y, z 좌표별로 따로 간선을 만들어 3n 개의 간선으로 접근하는 것이 더 효율적
 */

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    data class Edge(val from:Int, val to:Int, val cost:Int)

    val listX = mutableListOf<Pair<Int, Int>>()
    val listY = mutableListOf<Pair<Int, Int>>()
    val listZ = mutableListOf<Pair<Int, Int>>()
    val edges = mutableListOf<Edge>()

    repeat(size) { order ->
        val (x, y, z) = readLine().split(" ").map { it.toInt() }
        listX.add(Pair(x, order))
        listY.add(Pair(y, order))
        listZ.add(Pair(z, order))
    }

    listX.sortBy { it.first }
    for (i in 0 until listX.lastIndex) {
        val from = listX[i]
        val to = listX[i + 1]
        edges.add(Edge(from.second, to.second, to.first - from.first))
    }

    listY.sortBy { it.first }
    for (i in 0 until listY.lastIndex) {
        val from = listY[i]
        val to = listY[i + 1]
        edges.add(Edge(from.second, to.second, to.first - from.first))
    }

    listZ.sortBy { it.first }
    for (i in 0 until listZ.lastIndex) {
        val from = listZ[i]
        val to = listZ[i + 1]
        edges.add(Edge(from.second, to.second, to.first - from.first))
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

    var sum = 0L
    edges.sortBy { it.cost }
    for ((from, to, cost) in edges) {
        if (!isUnion(from, to)) {
            unionSet(from, to)
            sum += cost
        }
    }

    println(sum)
}