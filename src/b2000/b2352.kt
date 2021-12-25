import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val portCnt = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }
    val dp = IntArray(portCnt){1}

    for (i in 0 until portCnt) {
        for (j in i downTo 0) {
            if (arr[j] < arr[i]) {
                dp[i] = max(dp[j] + 1, dp[i])
            }
        }
    }
    println(dp.contentToString())
    println(dp.maxOrNull())
}