package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (students, relationCnt, money) = readLine().split(' ').map { it.toInt() }
    val prices = readLine().split(' ').mapIndexed { idx, str ->  Pair(idx + 1, str.toInt()) }

    data class Line(val from:Int, val to:Int, val price:Int)
    val relations = Array(relationCnt) {
        val (from, to) = readLine().split(' ').map { it.toInt() }
        Line(from, to, 0)
    }

    val disjointSet = IntArray(students + 1) { it }
    fun findSet(vertex: Int):Int {
        if (vertex != disjointSet[vertex])
            disjointSet[vertex] = findSet(disjointSet[vertex])

        return disjointSet[vertex]
    }

    fun isUnion(a:Int, b:Int):Boolean {
        return findSet(a) == findSet(b)
    }

    fun unionSet(a:Int, b:Int) {
        disjointSet[findSet(a)] = findSet(b)
    }

    relations.forEach {
        val (from, to, _) = it
        if (!isUnion(from, to)) {
            unionSet(from, to)
        }
    }

    var sum = 0
    prices.sortedBy { it.second }.forEach { (idx, price) ->
        if (!isUnion(0, idx)) {
            unionSet(0, idx)
            sum += price
        }
    }

    println(if (money >= sum) sum else "Oh no")
}