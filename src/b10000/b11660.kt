package b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = listOf(List(n + 1) {0}) + List(n) {
        listOf(0) + readLine().split(" ").map { it.toInt() }
    }
    val sum = MutableList(arr.size) {
        MutableList(arr.size) { 0 }
    }

    for (i in 1..n) {
        for (j in 1..n) {
            sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j]
        }
    }

    val out = System.out.bufferedWriter()
    repeat(m) {
        val (y1, x1, y2, x2) = readLine().split(" ").map { it.toInt() }
        val ans = sum[y2][x2] - sum[y1-1][x2] - sum[y2][x1-1] + sum[y1-1][x1-1]
        out.appendLine(ans.toString())
    }
    out.flush()
}