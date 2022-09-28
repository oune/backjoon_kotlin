package b10000

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    var treeStart = 1
    while(treeStart < n)
        treeStart *= 2

    data class Data(val idx:Int, val value:Int)

    val tree = Array(treeStart * 2) { Data(it, Int.MAX_VALUE) }

    fun Array<Data>.setData(idx:Int, value:Int) {
        tree[idx] = Data(idx, value)

        var i = idx / 2
        while (i > 0) {
            val leftIdx = i * 2
            val left = this[leftIdx]
            val rightIdx = leftIdx + 1
            val right = this[rightIdx]

            this[i] = if (left.value > right.value) right else left
            i /= 2
        }
    }

    fun Array<Data>.getMin(idx:Int, currentLeft:Int, currentRight:Int, left:Int, right:Int) :Data{
        if (left <= currentLeft && currentRight <= right)
            return this[idx]
        if (currentLeft > right)
            return Data(Int.MAX_VALUE, Int.MAX_VALUE)
        if (currentRight < left)
            return Data(Int.MAX_VALUE, Int.MAX_VALUE)

        val mid = (currentLeft + currentRight) / 2
        val leftMin = this.getMin(idx * 2, currentLeft, mid, left, right)
        val rightMin = this.getMin(idx * 2 + 1, mid + 1, currentRight, left, right)

        return if (leftMin.value > rightMin.value) rightMin else leftMin
    }

    fun Array<Data>.getMin(left:Int, right:Int) = this.getMin(1, 0, treeStart - 1, left, right)

    readLine().split(' ').map { it.toInt() }.forEachIndexed { index, i ->
        tree.setData(treeStart + index, i)
    }

    val out = System.out.bufferedWriter()
    val queryCnt = readLine().toInt()
    repeat(queryCnt) {
        val (type, a, b) = readLine().split(' ').map { it.toInt() }

        when(type) {
            1 -> {
               tree.setData(treeStart + a - 1, b)
            }
            2 -> {
                val min = tree.getMin(a - 1, b - 1).idx - treeStart + 1
                out.appendLine(min.toString())
            }
        }
    }
    out.flush()
}