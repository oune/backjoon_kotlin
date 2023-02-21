import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val numbs = readLine().split(" ").map { it.toInt() }

    var max = 0
    val selected = IntArray(numbs.size) { 0 }
    val visited = BooleanArray(numbs.size) { false }
    fun perm(depth:Int) {
        if (depth == numbs.size) {
            var sum = 0
            for (i in 0 until selected.lastIndex) {
                sum += abs(selected[i] - selected[i + 1])
            }

            max = max.coerceAtLeast(sum)
            return
        }

        for (i in selected.indices) {
            if (!visited[i]) {
                selected[depth] = numbs[i]

                visited[i] = true
                perm(depth + 1)
                visited[i] = false
            }
        }
    }

    perm(0)
    println(max)
}