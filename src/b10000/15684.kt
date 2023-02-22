fun main() = with(System.`in`.bufferedReader()) {
    val (width, cnt, height) = readLine().split(" ").map { it.toInt() }

    val ladder = List(height) {
        BooleanArray(width - 1) { false }
    }

    repeat(cnt) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        ladder[a - 1][b - 1] = true
    }

    fun check(): Boolean {
        val pos = IntArray(width) { it }
        for (line in ladder) {
            for (i in line.indices) {
                if (line[i]) {
                    val temp = pos[i]
                    pos[i] = pos[i + 1]
                    pos[i + 1] = temp
                }
            }
        }

        for (i in pos.indices) {
            if (i != pos[i])
                return false
        }
        return true
    }

    var min = Int.MAX_VALUE
    fun dfs(depth:Int, y:Int) {
        if (check()) {
            min = min.coerceAtMost(depth)
            return
        }

        for (i in y .. ladder.lastIndex) {
            for (j in 0 .. ladder[i].lastIndex) {
                if (ladder[i][j]) {
                    continue
                }

                val left = j - 1
                if (left in ladder[i].indices && ladder[i][left]) {
                    continue
                }

                val right = j + 1
                if (right in ladder[i].indices && ladder[i][right]) {
                    continue
                }

                if (depth < 3){
                    ladder[i][j] = true
                    dfs(depth + 1, i)
                    ladder[i][j] = false
                }
            }

        }
    }

    dfs(0, 0)
    println(if (min == Int.MAX_VALUE) -1 else min)
}