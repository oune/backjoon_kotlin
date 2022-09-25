package b2000

import kotlin.math.max
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }

    var start = 1
    while (start < n)
        start *= 2

    val minSet = IntArray(start * 2) { Int.MAX_VALUE }
    val maxSet = IntArray(start * 2) { Int.MIN_VALUE }

    fun setData(idx:Int, value:Int) {
        minSet[idx] = value
        maxSet[idx] = value

        var i = idx / 2
        while (i > 0) {
            minSet[i] = min(minSet[i*2], minSet[i*2 + 1])
            maxSet[i] = max(maxSet[i*2], maxSet[i*2 + 1])
            i /= 2
        }
    }

    fun getData(idx:Int, currentLeft:Int, currentRight:Int, left:Int, right:Int) : Pair<Int,Int>{
        if (left <= currentLeft && currentRight <= right)
            return Pair(minSet[idx], maxSet[idx])
        if (currentLeft > right)
            return Pair(Int.MAX_VALUE, Int.MIN_VALUE)
        if (currentRight < left)
            return Pair(Int.MAX_VALUE, Int.MIN_VALUE)

        val mid = (currentLeft + currentRight) / 2
        val leftValue = getData(idx *2, currentLeft, mid, left, right)
        val rightValue = getData(idx *2 + 1, mid + 1, currentRight, left, right)

        return Pair(min(leftValue.first, rightValue.first), max(leftValue.second, rightValue.second))
    }

    fun getData(left:Int, right:Int) : Pair<Int,Int> = getData(1, 0, start - 1, left, right)

    repeat(n) {
        setData(start + it, readLine().toInt())
    }

    val out = System.out.bufferedWriter()
    repeat(m) {
        val (a, b) = readLine().split(' ').map { it.toInt() }
        val res = getData(a - 1, b - 1)
        out.appendLine("${res.first} ${res.second}")
    }
    out.flush()
}