/*
DP
조합의 성질을 이용
 */
fun main() {
    val memo = Array(31) {
        IntArray(31) { -1 }
    }

    fun comb(n:Int, r:Int): Int {
        if (memo[n][r] == -1) {
            if (r == 0 || n == r)
                memo[n][r] = 1
            else
                memo[n][r] = comb(n - 1, r - 1) + comb(n - 1, r)
        }
        return memo[n][r]
    }

    val ans = List(readln().toInt()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        comb(m, n)
    }

    print(ans.joinToString("\n"))
}