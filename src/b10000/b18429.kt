package test.b10000

fun main() = with(System.`in`.bufferedReader()) {
    val (day, down) = readLine().split(' ').map { it.toInt() }
    val tools = readLine().split(' ').map { it.toInt() - down }
    val visited = BooleanArray(tools.size) { false }
    val selected = IntArray(tools.size) { 0 }

    var count = 0
    fun perm(depth:Int, end:Int) {
        if (depth == end) {
            var sum = 500

            if ( selected.all { tool ->
                sum += tool
                sum >= 500
            } ) count++

            return
        }

        tools.indices.forEach { idx ->
            if (!visited[idx]) {
                selected[depth] = tools[idx]
                visited[idx] = true
                perm(depth + 1, end)
                visited[idx] = false
            }
        }
    }
    perm(0, tools.size)

    println(count)
}