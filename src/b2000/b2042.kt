package b2000

import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(' ').filter { it != "" }.map { it.toInt() }

    var start = 1
    while (start < n)
        start *= 2

    val tree = LongArray(start * 2) { 0 }

    fun setDate(idx:Int, value: Long) : Long{
        tree[idx] = value

        var i = idx
        while(i > 1) {
            i /= 2
            tree[i] = tree[i*2] + tree[i*2 + 1]
        }

        return tree[i]
    }

    fun segTree(idx:Int, cl:Int, cr:Int, left:Int, right:Int) :Long {
        if (left <= cl && cr <= right)
            return tree[idx]
        if (cl > right)
            return 0
        if (cr < left)
            return 0

        val mid = (cl + cr) / 2
        val value1 = segTree(idx * 2, cl, mid, left, right)
        val value2 = segTree(idx * 2 + 1, mid + 1, cr, left, right)

        return value1 + value2
    }

    repeat(n) {
        val num = readLine().toLong()
        setDate(start + it, num)
    }

    val out = System.out.bufferedWriter()
    repeat(m + k) {

        val (_a, _b, _c) = readLine().split(' ')
        val a = _a.toInt()
        val b = _b.toInt()
        val c = _c.toLong()

        when (a) {
            1 -> {
                setDate(start + b - 1, c)
            }
            2 -> {
                out.appendLine(segTree(1, 0, start - 1, b - 1, c.toInt() - 1).toString())
            }
        }
    }
    out.flush()
}