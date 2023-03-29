/*
dp
f(n) = max( a(n), f(n-1) + f(1), ...)
50분 소요
 */
fun main() {
    val n = readln().toInt()
    val numbs = readln().split(" ").map { it.toInt() }

    val memo = IntArray(n + 1) { -1 }
    memo[0] = numbs[0]

    fun dp(idx:Int): Int {
        if (memo[idx] == -1) {
            val list = mutableListOf(numbs[idx - 1], numbs[0] * idx)

            for (i in 1 .. idx / 2) {
                list.add(dp(idx - i) + dp(i))
            }

            memo[idx] = list.max()
        }

        return memo[idx]
    }

    maxOf(1, 2, 3, 4, 5)

    println(dp(n))
}