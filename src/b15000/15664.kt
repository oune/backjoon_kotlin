fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbs = readLine().split(" ").map { it.toInt() }.sorted()
    val sb = StringBuilder()
    val set = HashSet<List<Int>>()

    val selected = IntArray(m) { 0 }
    fun comb(count:Int, start:Int) {
        if (count == m) {
            val list = selected.toList()
            if (list !in set)
                sb.appendLine(list.joinToString(" "))
                set.add(selected.toList())
            return
        }

        for (i in start .. numbs.lastIndex) {
            selected[count] = numbs[i]
            comb(count + 1, i + 1)
        }
    }

    comb(0, 0)
    print(sb)
}