fun main() = with(System.`in`.bufferedReader()) {
    val testcaseCnt = readLine().toInt()
    val sb = StringBuilder()

    repeat(testcaseCnt) {
        val (rowCnt, columnCnt) = readLine().split(" ").map { it.toInt() }

        data class Node(val from:Pair<Int,Int>, val to:Pair<Int,Int>, val cost:Int)
        val lines = mutableListOf<Node>()

        repeat(rowCnt) { row ->
            readLine().split(" ").map { it.toInt() }.forEachIndexed { index, cost ->
                lines.add(Node(Pair(index, row), Pair(index + 1, row), cost))
            }

        }

        repeat(rowCnt - 1) { column ->
            readLine().split(" ").map { it.toInt() }.forEachIndexed { index, cost ->
                lines.add(Node(Pair(index, column), Pair(index, column + 1), cost))
            }
        }

        val set = DisjointSet(columnCnt, rowCnt)
        val sorted = lines.sortedBy { it.cost }
        var sum = 0
        for ((from, to, cost) in sorted) {
            if (set.isUnion(from, to))
                continue

            set.unionSet(from, to)
            sum += cost
        }

        sb.appendLine(sum)
    }
    print(sb)
}

class DisjointSet(width:Int, height:Int) {
    private val parents = List(height) { i ->
        Array(width) { j -> Pair(j, i) }
    }

    private fun getParent(pair: Pair<Int, Int>): Pair<Int, Int> {
        return parents[pair.second][pair.first]
    }

    private fun setParent(idx: Pair<Int, Int>, value: Pair<Int, Int>) {
        parents[idx.second][idx.first] = value
    }

    fun findSet(num: Pair<Int,Int>): Pair<Int, Int> {
        if (num != getParent(num))
            setParent(num, findSet(getParent(num)))

        return getParent(num)
    }

    fun unionSet(a:Pair<Int, Int>, b:Pair<Int, Int>) {
        val pa = findSet(a)
        val pb = findSet(b)
        setParent(pa, pb)
    }

    fun isUnion(a:Pair<Int, Int>, b:Pair<Int, Int>): Boolean {
        return findSet(a) == findSet(b)
    }
}