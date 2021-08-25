val visited = Array(201){Array(201){Array(201){false} } }
val res = ArrayList<Int>()

var maxA = 0
var maxB = 0
var maxC = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b, c) = readLine().split(" ").map { it.toInt() }
    maxA = a
    maxB = b
    maxC = c

    dfs(0, 0, c)
    res.sorted().forEach {
        print("$it ")
    }
}

val pour = { a: Int, b: Int , maxB: Int->
    if (a + b > maxB) {
        Pair(a + b - maxB ,maxB)
    } else {
        Pair(0 ,a + b)
    }
}

val actionList = listOf<(Int, Int, Int) -> Triple<Int, Int, Int>>(
    { a: Int, b: Int, c: Int ->
        val (newA, newB) = pour(a, b, maxB)
        Triple(newA, newB, c)
    },
    { a: Int, b: Int, c: Int ->
        val (newA, newC) = pour(a, c, maxC)
        Triple(newA, b, newC)
    },{ a: Int, b: Int, c: Int ->
        val (newB, newA) = pour(b, a, maxA)
        Triple(newA, newB, c)
    },
    { a: Int, b: Int, c: Int ->
        val (newB, newC) = pour(b, c, maxC)
        Triple(a, newB, newC)
    },{ a: Int, b: Int, c: Int ->
        val (newC, newA) = pour(c, a, maxA)
        Triple(newA, b, newC)
    },
    { a: Int, b: Int, c: Int ->
        val (newC, newB) = pour(c, b, maxB)
        Triple(a, newB, newC)
    }
)

private fun dfs(a: Int, b: Int, c: Int) {
    visited[a][b][c] = true

    if (a == 0)
        res.add(c)

    for (action in actionList) {
        val t = action(a, b, c)

        if (!visited[t.first][t.second][t.third]) {
            dfs(t.first, t.second, t.third)
        }
    }
}