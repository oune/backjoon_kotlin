package b1000
fun main() = with(System.`in`.bufferedReader()) {
    val (r, c) = readLine().split(" ").map { it.toInt() }
    val arr = Array(r) {
        readLine().toCharArray()
    }

    var max = 0
    val alphabets = BooleanArray('Z' - 'A' + 1) { false }
    fun Array<CharArray>.dfs(x:Int, y:Int, depth:Int) {
        if (depth > max)
            max = depth

        val moveList = listOf(
            { a:Int, b:Int -> Pair(a + 1, b) },
            { a:Int, b:Int -> Pair(a - 1, b) },
            { a:Int, b:Int -> Pair(a, b + 1) },
            { a:Int, b:Int -> Pair(a, b - 1) },
        )

        moveList.forEach {
            val moved = it(x, y)

            if (moved.second in arr.indices) {
                if (moved.first in arr[y].indices) {
                    if (!alphabets[arr[moved.second][moved.first] - 'A']) {
                        alphabets[arr[moved.second][moved.first] - 'A'] = true
                        this.dfs(moved.first, moved.second, depth + 1)
                        alphabets[arr[moved.second][moved.first] - 'A'] = false
                    }
                }
            }
        }
    }

    alphabets[arr[0][0] - 'A'] = true
    arr.dfs(0,0, 1)

    println(max)
}