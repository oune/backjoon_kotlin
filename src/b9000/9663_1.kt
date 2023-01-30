package b9000
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    val map = IntArray(size) { -1 }

    fun IntArray.dfs():Int {
        fun IntArray.isPossible(row:Int):Boolean {
            for (i in 0 until row) {
                if (this[i] == this[row])
                    return false
                if (row - i == abs(this[row] - this[i]))
                    return false
            }
            return true
        }

        var count = 0;

        fun IntArray.dfs(row:Int) {
            if (row == size) {
                count++
            } else {
                for (i in this.indices) {
                    this[row] = i
                    if (this.isPossible(row)) {
                        this.dfs(row + 1)
                    }
                }
            }
        }
        this.dfs(0)

        return count
    }

    println(map.dfs())
}