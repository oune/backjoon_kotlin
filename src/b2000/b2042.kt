package b2000

import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(' ').map { it.toInt() }

    var start = 1
    while (start < n)
        start *= 2

    val tree = LongArray(2 * start - 1) { 0 }

    fun setDate(idx:Int, value: Long) {
        tree[idx] = value

        var i = idx
        while(i > 1) {
            i /= 2
            tree[i] = tree[i*2] + tree[i*2 + 1]
        }
    }
    fun segTree(idx:Int, cl:Int, cr:Int, left:Int, right:Int) :Long {
        if (left <= cl && cr <= right)
            return tree[idx]
        if (cr < left)
            return 0
        if (cl > right)
            return 0

        val value1 = segTree(idx * 2, cl, (cl + cr) / 2, left, right)
        val value2 = segTree(idx * 2 + 1, (cl + cr) / 2 + 1, cr, left, right)

        return value1 + value2
    }

    repeat(n) {
        val num = readLine().toLong()
        setDate(start + it, num)
    }

    val out = System.out.bufferedWriter()
    repeat(m + k) {
        val (a, b, c) = readLine().split(' ').map { it.toInt() }

        when (a) {
            1 -> {
                setDate(start + b, c.toLong())
            }
            2 -> {
                out.appendLine(segTree(1, 1, n, a, b).toString())
            }
        }
    }
    out.flush()
}