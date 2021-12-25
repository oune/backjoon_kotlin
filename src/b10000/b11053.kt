import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val data = readLine().split(" ").map { it.toInt() }
    val dp = Array(data.size) { 1 }

    for (i in 0 until data.size) {
        for (j in i downTo 0) {
            if (data[j] < data[i]){
                dp[i] = max(dp[j] + 1, dp[i])
            }
        }
    }
//    println(dp.contentDeepToString())
    println(dp.maxOrNull()!!)
}