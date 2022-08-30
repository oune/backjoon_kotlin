package test.b20000

fun main() = with(System.`in`.bufferedReader()) {
    val (vertexCnt, lineCnt) = readLine().split(" ").map { it.toInt() }
    data class Line(val a:Int, val b:Int, val price:Int)
    val lines = Array(lineCnt) {
        val (a, b, price) = readLine().split(" ").map { it.toInt() }
        Line(a, b, price)
    }.sortedBy { it.price }

    var disjointSet = IntArray(vertexCnt + 1) { it }
    fun findSet(vertex: Int) :Int{
        if (vertex != disjointSet[vertex])
            disjointSet[vertex] = findSet(disjointSet[vertex])
        return disjointSet[vertex]
    }

    fun isUnion(a:Int, b:Int) :Boolean{
        return disjointSet[findSet(a)] == disjointSet[findSet(b)]
    }

    fun unionSet(a:Int, b:Int) {
        disjointSet[findSet(a)] = disjointSet[findSet(b)]
    }

    val mstLines = lines.map {
        val (a, b, _) = it

        if (!isUnion(a, b)) {
            unionSet(a, b)
            it
        } else {
            Line(-1, -1, -1)
        }
    }.filter { it.price != -1 }

    val out = System.out.bufferedWriter()
    val questionCnt = readLine().toInt()
    repeat(questionCnt) {
        val readers = readLine().split(" ").map { it.toInt() }
        disjointSet = IntArray(vertexCnt + 1) { it }

        var sum = 0
        mstLines.forEach {
            val (a, b, price) = it

            fun unionSet(a:Int, b:Int) {
                val aLeader = findSet(a)
                val bLeader = findSet(b)

                if (aLeader in readers) {
                    disjointSet[bLeader] = disjointSet[aLeader]
                } else {
                    disjointSet[aLeader] = disjointSet[bLeader]
                }
            }

            if (!isUnion(a, b)) {
                if (!(findSet(a) in readers && findSet(b) in readers)) {
                    unionSet(a, b)
                    sum += price
                }
            }
        }

        out.appendLine(sum.toString())
    }
    out.flush()
}