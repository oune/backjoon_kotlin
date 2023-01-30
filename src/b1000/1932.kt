package b1000fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val map = Array(size) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 1 .. map.lastIndex) {
        for (j in map[i].indices) {
            val now = map[i][j]

            val left = if (j - 1 in map[i - 1].indices) {
                map[i - 1][j - 1]
            } else 0
            val right = if (j in map[i - 1].indices) {
                map[i - 1][j]
            } else 0

            map[i][j] = now + left.coerceAtLeast(right)
        }
    }

    println(map.last().max())
}