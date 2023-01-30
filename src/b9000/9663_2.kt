package b9000fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()

    val visited = BooleanArray(size) { false }
    val visitedCrossMinus = BooleanArray(size * 2) { false }
    val visitedCrossPlus = BooleanArray(size * 2) { false }

    fun isPossible(row: Int, column: Int): Boolean{
        return visited[column] || visitedCrossPlus[row + column] || visitedCrossMinus[row - column + size]
    }

    var count = 0
    fun dfs(row : Int) {
        if (row == size) {
            count++
            return
        }

        for (column in 0 until size) {
            if (!isPossible(row, column)) {
                visited[column] = true
                visitedCrossMinus[row - column + size] = true
                visitedCrossPlus[row + column] = true
                dfs(row + 1)
                visited[column] = false
                visitedCrossMinus[row - column + size] = false
                visitedCrossPlus[row + column] = false
            }
        }
    }
    dfs(0)
    println(count)
}