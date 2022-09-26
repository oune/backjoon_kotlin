package b1000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, q) = readLine().split(' ').map { it.toInt() }

    var treeStart = 1
    while (treeStart < n)
        treeStart *= 2

    fun LongArray.setData(idx:Int, value:Long) {
        this[idx] = value

        var i = idx / 2
        while(i > 0) {
            val left = this[i * 2]
            val right = this[i * 2 + 1]

            this[i] = left + right
            i /= 2
        }
    }

    fun LongArray.getSum(idx: Int, currentLeft: Int, currentRight: Int, left: Int, right: Int): Long {
        if (left <= currentLeft && currentRight <= right)
            return this[idx]
        if (currentLeft > right)
            return 0
        if (currentRight < left)
            return 0

        val mid = (currentLeft + currentRight) / 2
        val leftValue = this.getSum(idx * 2, currentLeft, mid, left, right)
        val rightValue = this.getSum(idx * 2 + 1, mid + 1, currentRight, left, right)

        return leftValue + rightValue
    }

    fun LongArray.getSum(left:Int, right:Int) :Long = this.getSum(1, 0, treeStart - 1, left, right)

    val tree = LongArray(treeStart * 2) { 0 }
    readLine().split(' ').map { it.toInt() }.forEachIndexed { idx, num ->
        tree.setData(treeStart + idx, num.toLong())
    }

    val out = System.out.bufferedWriter()
    repeat(q) {
        val (_start, _end, position, newValue) = readLine().split(' ').map { it.toInt() }
        val idx = position - 1
        val start = _start.coerceAtMost(_end) - 1
        val end = _end.coerceAtLeast(_start) - 1

        out.appendLine(tree.getSum(start, end).toString())
        tree.setData(treeStart + idx, newValue.toLong())
    }
    out.flush()
}