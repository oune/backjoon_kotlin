fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val inequalities = readLine().split(" ")
    val numbs = List(10) { it }

    var min : String? = null
    var max = ""

    val selected = IntArray(inequalities.size + 1)
    val visited = BooleanArray(numbs.size) { false }
    fun perm(depth:Int) {
        if (depth > 1) {
            val first = selected[depth - 2]
            val second = selected[depth - 1]
            when (inequalities[depth - 2]) {
                "<" -> {
                    if (first >= second)
                        return
                }
                ">" -> {
                    if (first <= second)
                        return
                }
            }
        }

        if (depth == selected.size) {
            if (min == null)
                min = selected.joinToString("")
            max = selected.joinToString("")
            return
        }

        for (i in numbs.indices) {
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
    println(min)
}