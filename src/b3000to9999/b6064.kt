package b3000to9999

fun main() = with(System.`in`.bufferedReader()) {
    val testCase = readLine().toInt()

    repeat(testCase) {
        val (m, n, x, y) = readLine().split(" ").map { it.toInt() }

        var res = -1
        var cnt = x - 1
        while (cnt < n * m) {
            if (cnt % n == y - 1) {
                res = cnt + 1
                break
            }

            cnt += m
        }

        println(res)
    }
}