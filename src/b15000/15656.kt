fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map { it.toInt() }
    val numbs = readLine().split(" ").map { it.toInt() }.sorted()
    val sb = StringBuilder()

    val selected = IntArray(m) { 0 }
    fun perm(depth:Int) {
        if (depth == m) {
            sb.appendLine(selected.joinToString(" "))
            return
        }

        for (i in numbs.indices) {
            selected[depth] = numbs[i]
            perm(depth + 1)
        }
    }
    perm(0)
    print(sb)
}