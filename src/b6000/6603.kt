fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()

    do {
        val input = readLine().split(" ").map { it.toInt() }
        val k = input.first()
        val numbs = input.drop(1).sorted()

        if (k == 0)
            continue

        fun Int.isVisited(idx:Int): Boolean {
            return this and (1 shl idx) != 0;
        }

        (0 until (1 shl k)).map { masking ->
            numbs.filterIndexed { index, _ -> masking.isVisited(index) }
        }.filter { it.size == 6 }.sortedWith(
            compareBy<List<Int>> { it[0] }
                .thenBy { it[1] }
                .thenBy { it[2] }
                .thenBy { it[3] }
                .thenBy { it[4] }
                .thenBy { it[5] }
        ).forEach {
            sb.appendLine(it.joinToString(" "))
        }

        sb.appendLine()
    } while(k != 0)

    print(sb)
}