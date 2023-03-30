/*
DP
 */
fun main() {
    val tc = readln().toInt()
    val ans = List(tc) {
        readln()
        val coins = readln().split(" ").map { it.toInt() }
        val money = readln().toInt()

        val memo = List(coins.size) {
            LongArray(money + 1) { 0 }
        }

        for (i in memo.indices) {
            for (j in memo[i].indices) {
                if (j == 0) {
                    memo[i][j] = 1
                } else {
                    val remain = j - coins[i]
                    val pre = if (i == 0) 0 else memo[i - 1][j]
                    val rest = if (remain >= 0) memo[i][remain] else 0
                    memo[i][j] = pre + rest
                }
            }
        }

        memo.last().last()
    }

    print(ans.joinToString("\n"))
}