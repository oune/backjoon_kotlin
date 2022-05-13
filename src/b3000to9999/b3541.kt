package test.b3000to9999

fun main() = with(System.`in`.bufferedReader()) {
    val (n , m) = readLine().split(" ").map { it.toInt() }

    var ans = Long.MAX_VALUE
    repeat(m) {
        val (u, d) = readLine().split(" ").map { it.toInt() }

        var left = 0L
        var right = n.toLong()
        while (left <= right) {
            val up = (left + right) / 2
            val down = n - up
            val floor = up * u - down * d

            if (floor > 0) {
                right = up - 1

                if (floor in 1 until ans) {
                    ans = floor
                }
            } else {
                left = up + 1
            }
        }
    }
    print(ans)
}