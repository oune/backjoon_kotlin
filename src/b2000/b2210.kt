import kotlin.collections.MutableSet as MutableSet

fun main() = with(System.`in`.bufferedReader()) {
    val arr = Array(5) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val res = mutableSetOf<Int>()
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            dfs(1, j, i, 0, res, arr)
        }
    }
    print(res.size)
}
val moves = listOf<(Int, Int) -> Pair<Int, Int>> (
    { x: Int, y: Int ->
        Pair(x + 1, y)
    },
    { x: Int, y: Int ->
        Pair(x, y + 1)
    },
    { x: Int, y: Int ->
        Pair(x - 1, y)
    },
    { x: Int, y: Int ->
        Pair(x, y - 1)
    }
)

private fun dfs(depth: Int, x: Int, y: Int, acc:Int ,res: MutableSet<Int>, arr:Array<IntArray>) {
    val accc = acc * 10 + arr[y][x]

    if (depth == 6) {
        res.add(accc)
        return
    }

    for (move in moves) {
        val (newX, newY) = move(x, y)
        if (newX in 0 until 5 && newY in 0 until 5) {
            dfs(depth + 1, newX, newY, accc, res, arr)
        }
    }
}