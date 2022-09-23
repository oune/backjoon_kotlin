package b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }

    var start = 1
    while (start < n)
        start *= 2

    val minTree = IntArray(start * 2) { Int.MAX_VALUE }

    fun IntArray.setData(idx:Int, data:Int) {
        this[idx] = data

        var i = idx / 2
        while (i > 0) {
            this[i] = this[i*2].coerceAtMost(this[i*2 + 1])
            i /= 2
        }
    }
    fun IntArray.getData(idx:Int, currentLeft:Int, currentRight:Int, left:Int, right:Int) :Int {
        if (left <= currentLeft && currentRight <= right)
            return this[idx]
        if (currentLeft > right)
            return Int.MAX_VALUE
        if (currentRight < left)
            return Int.MAX_VALUE

        val mid = (currentLeft + currentRight) / 2
        val value1 = this.getData(idx * 2, currentLeft, mid, left, right)
        val value2 = this.getData(idx * 2 + 1, mid + 1, currentRight, left, right)

        return value1.coerceAtMost(value2)
    }

    repeat(n) {
        val num = readLine().toInt()
        minTree.setData(start + it, num)
    }

    val out = System.out.bufferedWriter()
    repeat(m) {
        val (a, b) = readLine().split(' ').map { it.toInt() }
        out.appendLine(minTree.getData(1, 0, start - 1, a - 1, b - 1).toString())
    }
    out.flush()
}