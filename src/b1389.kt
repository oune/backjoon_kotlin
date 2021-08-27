import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val res = Array(n + 1){ Array(n + 1) { 1000000 } }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        res[a][b] = 1
        res[b][a] = 1
    }

    for (k in 1 .. n) {
        for (i in 1 .. n) {
            if (i == k)
                continue
            for (j in 1 .. n) {
                if (j == k || j == i)
                    continue

                res[i][j] = min(res[i][k] + res[k][j], res[i][j])
            }
        }
    }

    var min = Int.MAX_VALUE
    var ans = 0
    for (i in 1..n) {
        val sum = res[i].filter { it != 1000000 }.sum()
        if (sum < min) {
            ans = i
            min = sum
        }
    }

    print(ans)
}