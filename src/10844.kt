fun main() {
    val n = readln().toInt()
    val mod = 1000000000

    val dp = List(n + 1) {
        List(10) {
            IntArray(1 shl 10) { 0 }
        }
    }

    for (i in 1 .. 9)
        dp[1][i][1 shl i] = 1

    for (i in 2 .. n) {
        for (j in 0 .. 9) {
            val bit = 1 shl j
            for (masking in 0 until (1 shl 10)) {
                var sum = 0L
                if (j + 1 in dp[i - 1].indices)
                    sum += dp[i - 1][j + 1][masking]
                if (j - 1 in dp[i - 1].indices)
                    sum += dp[i - 1][j - 1][masking]

                sum %= mod
                dp[i][j][masking or bit] = sum.toInt()
            }
        }
    }

    var sum = 0
    for (i in dp[n].indices) {
        sum += dp[n][i].last()
        sum %= mod
    }

    println(sum)
}