import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val lineCnt = readLine().toInt()
    val lines = Array(lineCnt) {
        val (a, b) = readLine().split(" ").map{ it.toInt() }
        Pair(a, b)
    }
    val dp = Array(lineCnt) { 1 }
    lines.sortBy { it.first }
    lines.forEachIndexed { index, pair ->
        for (j in index downTo 0) {
            if (lines[j].second < pair.second) {
                dp[index] = max(dp[j] + 1, dp[index])
            }
        }
    }
    println(dp.contentDeepToString())
    print(lineCnt - dp.maxOrNull()!!)
}