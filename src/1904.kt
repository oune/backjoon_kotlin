/*
dp
11분 소요
 */
fun main() {
    val n = readln().toInt()

    val memo = IntArray(n + 1) { it }
    for (i in 3..n)
        memo[i] = (memo[i - 1] + memo[i - 2]) % 15746

    print(memo.last())
}