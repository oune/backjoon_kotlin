package b9000fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()

    val map = IntArray(size) { -1 }

    fun IntArray.isPossible(row : Int):Boolean {
        for (i in 0 until row) {
            if (this[i] == this[row])
                return false
            if (row - i == kotlin.math.abs(this[row] - this[i]))
                return false
        }
        return true
    }

    var ans = 0
    fun IntArray.dfs(row: Int) {
        if (row == size) {
            ans++
            return
        }

        for (i in 0 until size) {
            this[row] = i

            if (this.isPossible(row))
                this.dfs(row + 1)
        }
    }

    map.dfs(0)
    println(ans)
}