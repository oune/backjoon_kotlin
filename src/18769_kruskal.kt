fun main() = with(System.`in`.bufferedReader()) {
    val testcaseCnt = readLine().toInt()
    val sb = StringBuilder()

    repeat(testcaseCnt) {
        val (rowCnt, columnCnt) = readLine().split(" ").map { it.toInt() }

        data class Node(val from:Int, val to:Int, val cost:Int)
        val lines = mutableListOf<Node>()

        repeat(rowCnt) { row ->
            readLine().split(" ").map { it.toInt() }.forEachIndexed { index, cost ->
                lines.add(Node((index + row * columnCnt), (index + 1 + row * columnCnt), cost))
            }

        }

        repeat(rowCnt - 1) { row ->
            readLine().split(" ").map { it.toInt() }.forEachIndexed { index, cost ->
                lines.add(Node((index + row * columnCnt), (index + (1 + row) * columnCnt), cost))
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
    private val parents = IntArray(height * width) { it
    }
    fun findSet(num: Int): Int {
        if (num != parents[num])
            parents[num] = findSet(parents[num])

        return parents[num]
    }

    fun unionSet(a:Int, b:Int) {
        val pa = findSet(a)
        val pb = findSet(b)
        parents[pa] = pb
    }

    fun isUnion(a:Int, b:Int): Boolean {
        return findSet(a) == findSet(b)
    }
}