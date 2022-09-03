package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val arr = readLine().split(' ').map { it.toInt() }

    val isPossible = BooleanArray(arr.sum() + 1) { false }
    isPossible[0] = true

    val visited = IntArray(arr.size) { 0 }
    fun subSet(depth:Int, end:Int) {
        if (depth == end) {
            var sum = 0
            visited.forEachIndexed { index, isVisited ->
                sum += arr[index] * isVisited
            }

            if (sum in isPossible.indices)
                isPossible[sum] = true

            return
        }

        listOf(1, 0, -1).forEach {
            visited[depth] = it
            subSet(depth + 1, end)
        }
    }

    subSet(0, arr.size)

    val ans = isPossible.count { !it }
    println(ans)
}