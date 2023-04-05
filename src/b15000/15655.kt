fun main() = with(System.`in`.bufferedReader()) {
    val (_, m) = readLine().split(" ").map { it.toInt() }
    val numbs = readLine().split(" ").map { it.toInt() }.sorted()
    val sb = StringBuilder();

    val selected = IntArray(m) { 0 }
    fun comb (count: Int, start:Int) {
        if (count == m) {
            sb.appendLine(selected.joinToString (" "))
            return
        }

        for (i in start..numbs.lastIndex) {
            selected[count] = numbs[i]
            comb(count + 1, i + 1)
        }
    }
    comb(0, 0)
    print(sb)
}