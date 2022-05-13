package test.b3000to9999

fun main() = with(System.`in`.bufferedReader()) {
    val (n , m) = readLine().split(" ").map { it.toInt() }
    val elevators = Array(m) {
        val (u, d) = readLine().split(" ").map { it.toInt() }
        Pair(u, d)
    }

    var ans = Long.MAX_VALUE
    elevators.forEach {
        var left = 0L
        var right = n.toLong()
        while (left <= right) {
            val up = (left + right) / 2
            val down = n - up
            val floor = up * it.first - down * it.second

            if (floor >= 0) {
                right = up - 1

                if (floor < ans) {
                    ans = floor
                }
            } else {
                left = up + 1
            }
        }
    }
    print(ans)
}