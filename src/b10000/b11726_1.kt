package b10000

val dp = MutableList<Int>(1001){ 0 }

fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine().toInt()
    dp[1] = 1
    dp[2] = 2
    print(dp(num))
}

fun dp(num : Int) :Int {
    if (dp[num] == 0) {
        dp[num] = dp(num - 1) + dp(num - 2)
        dp[num] %= 10007
    }
    return dp[num]
}

