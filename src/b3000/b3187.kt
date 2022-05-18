package b9000

fun main() = with(System.`in`.bufferedReader()){
    val (r, c) = readLine().split(" ").map { it.toInt() }
    val map = Array(r) {
        readLine().toCharArray()
    }
    val visited = Array(r) {
        BooleanArray(c) { false }
    }

    var totalSheep = 0
    var totalWolf = 0
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (map[i][j] == '#')
                continue

            val (sheep, wolf) = dfs(map, visited, i, j, Pair(0, 0))
            if (wolf >= sheep) {
                totalWolf += wolf
            } else if (wolf < sheep) {
                totalSheep += sheep
            }
        }
    }

    print("$totalSheep $totalWolf")
}

fun dfs(map:Array<CharArray>, visited:Array<BooleanArray>, i:Int, j:Int, acc:Pair<Int, Int>) :Pair<Int, Int>{
    if (i < 0 || i >= map.size)
        return acc
    if (j < 0 || j >= map[i].size)
        return acc
    if (visited[i][j])
        return acc
    if (map[i][j] == '#')
        return acc

    var res = if (map[i][j] == 'v') {
        Pair(acc.first, acc.second + 1)
    } else if (map[i][j] == 'k') {
        Pair(acc.first + 1, acc.second)
    } else {
        acc
    }

    visited[i][j] = true

    res = dfs(map, visited, i + 1, j, res)
    res = dfs(map, visited, i, j + 1, res)
    res = dfs(map, visited, i - 1, j, res)
    res = dfs(map, visited, i, j - 1, res)

    return res
}