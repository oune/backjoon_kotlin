fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    val start = getStart(n)
    val size = 2 * start - 1
    val segment = IntArray(size) { 0 }

    repeat(n) { setData(segment, start + it, readLine().toInt()) }

    val out = System.out.bufferedWriter()
    repeat(m + k) {
        val (a, b, c) = readLine().split(" ").map{ it.toInt() }
        if (a == 1) {
            setData(segment, b, c)
        } else {
            out.appendLine(solve(segment, 1, n, 1, b, c).toString())
        }
    }
    out.flush()
}

fun getStart(num: Int): Int {
    var start = 1
    while (start < num) {
        start *= 2
    }
    return start
}

fun setData(arr:IntArray, idx: Int, value: Int) {
    val v = value - arr[idx]
    var index = idx
    while (index != 0) {
        arr[index] += v
        index /= 2
    }
}

fun solve(arr:IntArray, idx:Int, cl:Int, cr:Int , left:Int, right:Int) :Int {
    if (left <= cl && cr <= right) {
        return arr[idx]
    }

    if (cr < left)
        return 0
    if (cl > right)
        return 0

    val v1 = solve(arr, idx * 2, cl, (cl + cr)/ 2, left, right)
    val v2 = solve(arr, idx * 2 + 1, (cl + cr)/ 2 + 1, cr, left, right)

    return v1 + v2
}