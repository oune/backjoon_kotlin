package b3000to9999

fun main() = with(System.`in`.bufferedReader()) {
    val testCase = readLine().toInt()

    repeat(testCase) {
        val (m, n, x, y) = readLine().split(" ").map { it.toInt() }

        var count = x
        var newY = x

        for (i in 0 until n) {
            val newX = if (newY % n == 0)
                n
            else
                newY % n

            if (newX == y)
                break

            newY = newX + m
            count += m
        }
    }
}