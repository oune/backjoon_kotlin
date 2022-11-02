package b1000

fun main () = with(System.`in`.bufferedReader()) {
    val (n, east, west, south, north) = readLine().split(' ').map { it.toInt() }

    val visited = Array(50) {
        BooleanArray(50) { false }
    }

    fun aaa(depth:Int, probability:Double, x:Int, y:Int) :Double {
        if (depth == n)
            return probability

        visited[x][y] = true

        var sum = 0.0
        if (!visited[x + 1][y])
            sum += aaa(depth + 1,probability / 100 * east, x + 1, y)
        if (!visited[x - 1][y])
            sum += aaa(depth + 1, probability / 100 * west, x - 1, y)
        if (!visited[x][y + 1])
            sum += aaa(depth + 1, probability / 100 * north, x, y + 1)
        if (!visited[x][y - 1])
            sum += aaa(depth + 1, probability / 100 * south, x, y - 1)

        visited[x][y] = false
        return sum
    }

    print(aaa(0,1.0, 25, 25))
}