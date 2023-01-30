package b10000fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val props = Array(n) {
        readLine().split(" ").map { it.toInt() }
    }

    val dp = Array(n) {
        IntArray(k + 1) { 0 }
    }

    for (i in dp.indices) {
        val (weight, value) = props[i]

        for (j in dp[i].indices) {
            if (i - 1 in dp.indices) {
                dp[i][j] = dp[i-1][j]
            }

            if (j >= weight) {
                val remainWeight = j - weight
                val remainValue = if (i - 1 in dp.indices) dp[i - 1][remainWeight] else 0

                dp[i][j] = dp[i][j].coerceAtLeast(value + remainValue)
            }
        }
    }

    println(dp.last().last())
}