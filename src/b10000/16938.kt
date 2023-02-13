fun main() = with(System.`in`.bufferedReader()) {
    val (problemCnt, lowerDifficulty, upperDifficulty, minimumInterval) = readLine().split(" ").map { it.toInt() }
    val numbs = readLine().split(" ").map { it.toInt() }

    fun Int.isVisited(idx:Int): Boolean {
        return this and (1 shl idx) != 0
    }

    var count = 0;
    for (masking in 0 until (1 shl problemCnt)) {
        val filtered = numbs.filterIndexed { index, _ -> masking.isVisited(index)}

        if (filtered.size < 2)
            continue

        val sum = filtered.sum()
        val min = filtered.min()
        val max = filtered.max()
        val distance = max - min

        if (sum in lowerDifficulty .. upperDifficulty) {
            if (distance >= minimumInterval) {
                count++
            }
        }
    }

    println(count)
}