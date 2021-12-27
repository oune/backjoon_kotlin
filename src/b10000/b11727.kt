package b10000

fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()

    val dp = IntArray(num + 1) { 0 }
    if (dp.size > 1)
        dp[1] = 1
    if (dp.size > 2)
        dp[2] = 3

    for (i in 3 .. num)
        dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007

    print(dp[num])
}