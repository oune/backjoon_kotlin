fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    val dp = IntArray(num + 1) { it }

    for (i in 3 .. num) {
        dp[i] = dp[i - 1] + dp[i - 2]
        dp[i] %= 10007
    }
    print(dp[num])
}