fun main() {
    val(n, k) = readln().split(" ").map { it.toInt() }

    val memo = List(n) {
        IntArray((it + 1).coerceAtMost(k)) // 이항계수 계산시에 k를 넘어가는 부분까지는 계산할 필요가 없음
    }

    for (i in memo.indices) {
        for (j in memo[i].indices) {
            memo[i][j] = if (j == 0 || i == j) 1 else memo[i - 1][j - 1] + memo[i - 1][j]
        }
    }

    print(memo.joinToString("\n") { it.joinToString (" ")} )
}